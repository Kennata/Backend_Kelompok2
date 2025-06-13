/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Kos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BackendTubes.Kamar.Kamar;
import com.example.BackendTubes.Kamar.KamarRepository;
import com.example.BackendTubes.Kamar.KamarService;

/**
 *
 * @author LENOVO
 */
@Service
public class KosService {

    private final KosRepository kosRepository;
    private final KamarRepository kamarRepository;
    private final KamarService kamarService;

    @Autowired
    public KosService(KosRepository kosRepository, KamarRepository kamarRepository, KamarService kamarService) {
        this.kosRepository = kosRepository;
        this.kamarRepository = kamarRepository;
        this.kamarService = kamarService;
    }

    public Map<String, Object> addKos(KosDTO kosDTO) {
        Optional<Kos> existingKos = kosRepository.findByNamaKos(kosDTO.getNamaKos());
        Map<String, Object> response = new HashMap<>();

        if (existingKos.isPresent()) {
            response.put("message", "Nama kos sudah ada");
            return response;
        }

        Kos kos = new Kos();
        kos.setNamaKos(kosDTO.getNamaKos());
        kos.setJumlahKamar(kosDTO.getJumlahKamar());
        kos.setHarga(kosDTO.getHarga());
        kos.setAlamat(kosDTO.getAlamat());
        kos.setDeskripsi(kosDTO.getDeskripsi());
        kos.setTipeKos(kosDTO.getTipeKos());
        List<Kamar> dataKamar = new ArrayList<>();
        for (int i = 1; i <= kosDTO.getJumlahKamar(); i++) {
            Kamar kamar = new Kamar();
            kamar.setNoKamar(i);
            kamar.setStatus("Kosong");
            dataKamar.add(kamar);
            kamarRepository.save(kamar);
        }
        kos.setDataKamar(dataKamar);
        kosRepository.save(kos);
        response.put("message", kos.getNamaKos() + " telah berhasil ditambahkan");
        response.put("harga", kos.getHarga());
        return response;
    }

    public Map<String, Object> deleteKos(Long id) {
        Optional<Kos> existingId = kosRepository.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (existingId.isEmpty()) {
            response.put("message", "Kos tidak ditemukan");
            return response;
        }

        kosRepository.deleteById(id);
        response.put("message", "Kos dengan id " + id + " telah terhapus");
        return response;
    }

    public Map<String, Object> editKos(Long id, KosDTO kosDTO) {
        Optional<Kos> existingId = kosRepository.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (existingId.isEmpty()) {
            response.put("message", "Kos tidak ditemukan");
            return response;
        }

        Kos kos = existingId.get();
        if (kos.getHarga() != kosDTO.getHarga()){
            kos.getDataKamar().forEach(kamar -> {
                if (kamar.getStatus().equals("Terisi")){
                    kamar.getDataPenghuni().getRiwayatPembayaran().forEach(pembayaran -> {
                        pembayaran.setNominalPembayaran(kosDTO.getHarga());
                    });
                }
            });
        }
        kos.setNamaKos(kosDTO.getNamaKos());
        kos.setHarga(kosDTO.getHarga());
        kos.setDeskripsi(kosDTO.getDeskripsi());
        kosRepository.save(kos);

        response.put("message", "Kos sudah diupdate");
        return response;
    }

    public Map<String, Object> viewKos() {
        List<Kos> kosList = kosRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> hasil = new ArrayList<>();

        for (Kos k : kosList) {
            Map<String, Object> kosMap = new LinkedHashMap<>();
            kosMap.put("id", k.getId());
            kosMap.put("namaKos", k.getNamaKos());
            kosMap.put("alamat", k.getAlamat());
            kosMap.put("jumlahKamar", k.getJumlahKamar());
            kosMap.put("deskripsi", k.getDeskripsi());
            kosMap.put("tipeKos", k.getTipeKos());
            kosMap.put("harga", k.getHarga());
            Map<String, Object> kamarMap = kamarService.viewKamar(k.getId());
            kosMap.putAll(kamarMap);
            hasil.add(kosMap);
        }
        response.put("dataKos", hasil);
        return response;
    }
}
