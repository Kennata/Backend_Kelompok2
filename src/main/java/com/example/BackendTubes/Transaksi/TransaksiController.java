package com.example.BackendTubes.Transaksi;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {
    private final TransaksiService transaksiService;

    @Autowired
    public TransaksiController(TransaksiService transaksiService) {
        this.transaksiService = transaksiService;
    }

    @PostMapping("/{penghuniId}")
    public Map<String, Object> addTransaksi(@PathVariable Long penghuniId, @RequestBody TransaksiDTO transaksiDTO){
        return transaksiService.addTransaksi(penghuniId, transaksiDTO);
    }
}
