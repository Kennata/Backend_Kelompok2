package com.example.BackendTubes.Transaksi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BackendTubes.Pembayaran.PembayaranRepository;
import com.example.BackendTubes.Penghuni.Penghuni;
import com.example.BackendTubes.Penghuni.PenghuniRepository;

@Service
public class TransaksiService {
    private final TransaksiRepository transaksiRepository;
    private final PenghuniRepository penghuniRepository;
    private final PembayaranRepository pembayaranRepository;

    @Autowired
    public TransaksiService(PenghuniRepository penghuniRepository, TransaksiRepository transaksiRepository, PembayaranRepository pembayaranRepository) {
        this.penghuniRepository = penghuniRepository;
        this.transaksiRepository = transaksiRepository;
        this.pembayaranRepository = pembayaranRepository;
    }

    public Map<String, Object> addTransaksi(Long id, TransaksiDTO transaksiDTO){
        Optional<Penghuni> existingId = penghuniRepository.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (existingId.isEmpty()){
            response.put("message","Penghuni tidak ditemukan");
            return response;
        }
        Penghuni penghuni = existingId.get();
        Transaksi transaksi = new Transaksi(transaksiDTO.getNominal(), LocalDate.now());
        transaksi.setPenghuni(penghuni);
        transaksiRepository.save(transaksi);
        penghuni.getTransaksi().add(transaksi);
        penghuni.getRiwayatPembayaran().forEach((pembayaran) -> {
            if (transaksi.getTanggalPembayaran().isAfter(pembayaran.getTanggalAwal().minusDays(1)) && transaksi.getTanggalPembayaran().isBefore(pembayaran.getTanggalAkhir().plusDays(1))){
                pembayaran.setNominalPembayaran(pembayaran.getNominalPembayaran()-transaksi.getNominal());
                if (pembayaran.getNominalPembayaran() == 0){
                    pembayaran.setStatus("Lunas");
                }
            }
            pembayaranRepository.save(pembayaran);
        });
        penghuniRepository.save(penghuni);
        response.put("message","Transaksi sudah ditambahkan");
        return response;
    }

    public Map<String, Object> viewTransaksi(Long id){
        Penghuni penghuni = penghuniRepository.getReferenceById(id);
        List<Transaksi> transaksi = penghuni.getTransaksi();
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> hasil = new ArrayList<>();
        transaksi.forEach(t -> {
            Map<String, Object> mapTrans = new HashMap<>();
            mapTrans.put("nominal", t.getNominal());
            mapTrans.put("tanggalPembayaran", t.getTanggalPembayaran());
            hasil.add(mapTrans);
        });
        response.put("dataTransaksi",hasil);
        return response;
    }
}
