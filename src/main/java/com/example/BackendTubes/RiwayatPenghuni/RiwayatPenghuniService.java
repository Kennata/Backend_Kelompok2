package com.example.BackendTubes.RiwayatPenghuni;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BackendTubes.Kos.Kos;
import com.example.BackendTubes.Kos.KosRepository;

@Service
public class RiwayatPenghuniService {

    private final RiwayatPenghuniRepository riwayatRepo;
    private final KosRepository kosRepo;

    @Autowired
    public RiwayatPenghuniService(RiwayatPenghuniRepository riwayatRepo, KosRepository kosRepo) {
        this.riwayatRepo = riwayatRepo;
        this.kosRepo = kosRepo;
    }

    public List<RiwayatPenghuni> getRiwayatPenghuni() {
        return riwayatRepo.findAll();
    }

    public void addNewRiwayatPenghuni(RiwayatPenghuni riwayatPenghuni) {
        Long kosId = riwayatPenghuni.getKos().getId();
        Kos kos = kosRepo.getKosById(kosId)
                .orElseThrow(() -> new RuntimeException("Kos tidak ditemukan"));

        riwayatPenghuni.setKos(kos);
        riwayatRepo.save(riwayatPenghuni);
    }
}
