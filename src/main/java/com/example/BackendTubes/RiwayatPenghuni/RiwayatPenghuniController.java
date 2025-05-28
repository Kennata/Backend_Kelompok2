package com.example.BackendTubes.RiwayatPenghuni;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/riwayat-penghuni")
public class RiwayatPenghuniController {

    @Autowired
    private RiwayatPenghuniRepository repository;

    @GetMapping
    public List<RiwayatPenghuni> getAll() {
        return repository.findAll();
    }
}
