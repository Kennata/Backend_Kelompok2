package com.example.BackendTubes.Pembayaran;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/pembayaran")
public class PembayaranController {
    private final PembayaranService pembayaranService;

    @Autowired
    public PembayaranController(PembayaranService pembayaranService) {
        this.pembayaranService = pembayaranService;
    }

    @GetMapping
    public List<Pembayaran> getPembayaran(){
        return pembayaranService.getPembayaran();
    }

    @PostMapping
    public void addNewPembayaran(@RequestBody Pembayaran pembayaran){
        pembayaranService.addNewPembayaran(pembayaran);
    }
}
