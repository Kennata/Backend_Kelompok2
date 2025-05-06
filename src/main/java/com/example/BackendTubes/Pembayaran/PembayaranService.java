package com.example.BackendTubes.Pembayaran;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PembayaranService {
    private final PembayaranRepository pembayaranRepository;

    @Autowired
    public PembayaranService(PembayaranRepository pembayaranRepository) {
        this.pembayaranRepository = pembayaranRepository;
    }

    public void addNewPembayaran(Pembayaran pembayaran){
        pembayaranRepository.save(pembayaran);
    }

    public List<Pembayaran> getPembayaran(){
        return pembayaranRepository.findAll();
    }
}
