/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Kamar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BackendTubes.Kos.Kos;
import com.example.BackendTubes.Kos.KosRepository;
import com.example.BackendTubes.Pembayaran.Pembayaran;
import com.example.BackendTubes.Pembayaran.PembayaranRepository;
import com.example.BackendTubes.Penghuni.Penghuni;
import com.example.BackendTubes.Penghuni.PenghuniRepository;
import com.example.BackendTubes.TanggalCounter;
import com.example.BackendTubes.Transaksi.TransaksiService;

import jakarta.transaction.Transactional;

/**
 *
 * @author LENOVO
 */
@Service
public class KamarService {

    private final KosRepository kosRepository;
    private final KamarRepository kamarRepository;
    private final PenghuniRepository penghuniRepository;
    private final PembayaranRepository pembayaranRepository;
    private final TransaksiService transaksiService;

    @Autowired
    public KamarService(KamarRepository kamarRepository, KosRepository kosRepository, PembayaranRepository pembayaranRepository, PenghuniRepository penghuniRepository, TransaksiService transaksiService) {
        this.kamarRepository = kamarRepository;
        this.kosRepository = kosRepository;
        this.pembayaranRepository = pembayaranRepository;
        this.penghuniRepository = penghuniRepository;
        this.transaksiService = transaksiService;
    }

    public Map<String, Object> viewKamar(Long Id) {
        Optional<Kos> cekKos = kosRepository.findById(Id);
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> hasil = new ArrayList<>();
        if (cekKos.isEmpty()) {
            response.put("message", "Kos Tidak Ditemukan");
            return response;
        }
        Kos kos = cekKos.get();
        List<Kamar> dataKamar = kos.getDataKamar();
        for (Kamar k : dataKamar) {
            Map<String, Object> kamarMap = new LinkedHashMap<>();
            kamarMap.put("noKamar", k.getNoKamar());
            kamarMap.put("status", k.getStatus());
            Map<String, Object> penghuniMap = viewPenghuni(Id, k.getNoKamar());
            kamarMap.putAll(penghuniMap);
            hasil.add(kamarMap);
        }
        response.put("dataKamar", hasil);
        return response;
    }

    @Transactional
    public Map<String, Object> assignPenghuni(Long id, int noKamar, Long penghuniId) {
        TanggalCounter tanggal = new TanggalCounter();
        Optional<Kamar> cekKamar = kamarRepository.findByKosIdAndNoKamar(id, noKamar);
        Kos kos = kosRepository.getReferenceById(id);
        Penghuni p = penghuniRepository.getReferenceById(penghuniId);
        Map<String, Object> response = new HashMap<>();
        if (cekKamar.isEmpty()) {
            response.put("message", "Kamar Tidak Ditemukan");
            return response;
        }
        Kamar kamar = cekKamar.get();
        if (p.getKamar() != null){
            Kamar kamarSebelum = p.getKamar();
            kamarSebelum.setStatus("Kosong");
            kamarRepository.save(kamarSebelum);
        }
        if (kamar.getStatus().equals("Terisi")) {
            response.put("message", "Kamar Sudah Terisi");
            return response;
        }
        kamar.setStatus("Terisi");
        pembayaranRepository.deleteByPenghuniId(penghuniId);
        LocalDate tanggalSekarang = tanggal.getTanggalSekarang();
        List<Pembayaran> riwayatPembayaran = p.getRiwayatPembayaran();
        riwayatPembayaran.clear();
        for (int i=0;i<2;i++){
            Pembayaran pembayaran = new Pembayaran(tanggalSekarang,tanggalSekarang.plusYears(1), kos.getHarga(), "Belum Lunas", p);
            riwayatPembayaran.add(pembayaran);
            pembayaranRepository.save(pembayaran);
            tanggalSekarang = tanggalSekarang.plusYears(1);
        }
        p.setRiwayatPembayaran(riwayatPembayaran);
        p.setKamar(kamar);
        penghuniRepository.save(p);
        kamarRepository.save(kamar);
        response.put("message", "Penghuni Sudah di Assign");
        return response;
    }

    public Map<String, Object> viewPenghuni(Long id, int noKamar) {
        Optional<Kamar> cekKamar = kamarRepository.findByKosIdAndNoKamar(id, noKamar);
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> hasil = new LinkedHashMap<>();

        if (cekKamar.isEmpty()) {
            response.put("message", "Kos Tidak Ditemukan");
            return response;
        }

        Kamar kamar = cekKamar.get();
        Penghuni dataPenghuni = kamar.getDataPenghuni();

        if (dataPenghuni != null) {
            Penghuni p = dataPenghuni;
            hasil.put("id", p.getId());
            hasil.put("nama", p.getNama());
            hasil.put("jenisKelamin", p.getJenisKelamin());
            hasil.put("usia", p.getUsia());
            hasil.put("nomorHp", p.getNomorHp());
            hasil.put("pekerjaan", p.getPekerjaan());
            hasil.put("kontakDarurat", p.getKontakDarurat());
            hasil.put("jenisKendaraan", p.getJenisKendaraan());
            hasil.put("platKendaraan", p.getPlatKendaraan());
            hasil.put("riwayatPembayaran", p.getRiwayatPembayaran());
            Map<String, Object> mapTransaksi = transaksiService.viewTransaksi(p.getId());
            hasil.putAll(mapTransaksi);
        }

        response.put("dataPenghuni", hasil);
        return response;
    }

    public Map<String, Object> kosongKamar(Long id, int noKamar) {
        Optional<Kamar> cekKamar = kamarRepository.findByKosIdAndNoKamar(id, noKamar);
        Map<String, Object> response = new HashMap<>();

        if (cekKamar.isEmpty()) {
            response.put("message", "Kamar Tidak Ditemukan");
            return response;
        }

        Kamar kamar = cekKamar.get();

        if (kamar.getStatus().equals("Kosong")) {
            response.put("message", "Kamar Sudah Kosong");
            return response;
        }

        Penghuni penghuni = kamar.getDataPenghuni(); // get the penghuni (karena sekarang OneToOne)
        if (penghuni != null) {
            penghuni.setKamar(null); // putuskan relasi dari sisi Penghuni
            penghuniRepository.save(penghuni); // simpan perubahan penghuni
        }

        kamar.setStatus("Kosong");
        kamarRepository.save(kamar); // simpan status kamar

        response.put("message", "Kamar Berhasil Dikosongkan");
        return response;
    }

}
