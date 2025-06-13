package com.example.BackendTubes.Pembayaran;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BackendTubes.Penghuni.Penghuni;
import com.example.BackendTubes.Penghuni.PenghuniRepository;
import com.example.BackendTubes.TanggalCounter;

@Service
public class PembayaranService {
    private final PembayaranRepository pembayaranRepository;
    private final PenghuniRepository penghuniRepository;

    @Autowired
    public PembayaranService(PembayaranRepository pembayaranRepository, PenghuniRepository penghuniRepository) {
        this.pembayaranRepository = pembayaranRepository;
        this.penghuniRepository = penghuniRepository;
    }

    public void addNewPembayaran(PembayaranRequest pembayaran){
        Penghuni penghuni = penghuniRepository.findById(pembayaran.getPenghuniId())
                .orElseThrow(() -> new RuntimeException("Penghuni not found"));

        Pembayaran newPembayaran = new Pembayaran();
        TanggalCounter tanggal = new TanggalCounter();
        newPembayaran.setTanggalAwal(tanggal.getTanggalSekarang());
        newPembayaran.setTanggalAkhir(tanggal.getTanggalDepan());
        newPembayaran.setNominalPembayaran(pembayaran.getNominalPembayaran());
        newPembayaran.setStatus(pembayaran.getStatus());
        newPembayaran.setPenghuni(penghuni);
        pembayaranRepository.save(newPembayaran);
    }

    public List<Pembayaran> getPembayaran(){
        return pembayaranRepository.findAll();
    }

    public List<Pembayaran> getPembayaranByPenghuniId(Long penghuniId){
        return pembayaranRepository.findAllByPenghuniId(penghuniId);
    }
}
