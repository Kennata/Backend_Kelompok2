package com.example.BackendTubes.Pembayaran;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/pembayaran")
public class PembayaranController {
    private final PembayaranService pembayaranService;

    @Autowired
    public PembayaranController(PembayaranService pembayaranService) {
        this.pembayaranService = pembayaranService;
    }


    @PostMapping
    public void addNewPembayaran(@RequestBody Pembayaran pembayaran){
        pembayaranService.addNewPembayaran(pembayaran);
    }
}
