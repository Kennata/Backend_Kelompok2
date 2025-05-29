package com.example.BackendTubes.RiwayatPenghuni;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiwayatPenghuniService {

    private final RiwayatPenghuniRepository riwayatPenghuniRepository;

    @Autowired
    public RiwayatPenghuniService(RiwayatPenghuniRepository riwayatPenghuniRepository) {
        this.riwayatPenghuniRepository = riwayatPenghuniRepository;
    }

    // Create or Update RiwayatPenghuni
    public RiwayatPenghuni saveRiwayatPenghuni(RiwayatPenghuni riwayatPenghuni) {
        if (riwayatPenghuni.getId() == null || riwayatPenghuni.getId().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        if (riwayatPenghuni.getKos() == null) {
            throw new IllegalArgumentException("Kos cannot be null");
        }
        if (riwayatPenghuni.getTanggalMasuk() == null || riwayatPenghuni.getTanggalMasuk().isEmpty()) {
            throw new IllegalArgumentException("Tanggal Masuk cannot be null or empty");
        }
        return riwayatPenghuniRepository.save(riwayatPenghuni);
    }

    // Get all RiwayatPenghuni
    public List<RiwayatPenghuni> getAllRiwayatPenghuni() {
        return riwayatPenghuniRepository.findAll();
    }

    // Get RiwayatPenghuni by ID
    public Optional<RiwayatPenghuni> getRiwayatPenghuniById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        return riwayatPenghuniRepository.findById(id);
    }

    // Update RiwayatPenghuni
    public RiwayatPenghuni updateRiwayatPenghuni(String id, RiwayatPenghuni updatedRiwayat) {
        Optional<RiwayatPenghuni> existingRiwayat = riwayatPenghuniRepository.findById(id);
        if (existingRiwayat.isEmpty()) {
            throw new RuntimeException("RiwayatPenghuni with ID " + id + " not found");
        }
        RiwayatPenghuni riwayat = existingRiwayat.get();
        if (updatedRiwayat.getKos() != null) {
            riwayat.setKos(updatedRiwayat.getKos());
        }
        if (updatedRiwayat.getTanggalMasuk() != null) {
            riwayat.setTanggalMasuk(updatedRiwayat.getTanggalMasuk());
        }
        if (updatedRiwayat.getTanggalKeluar() != null) {
            riwayat.setTanggalKeluar(updatedRiwayat.getTanggalKeluar());
        }
        return riwayatPenghuniRepository.save(riwayat);
    }

    // Delete RiwayatPenghuni by ID
    public void deleteRiwayatPenghuni(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        if (!riwayatPenghuniRepository.existsById(id)) {
            throw new RuntimeException("RiwayatPenghuni with ID " + id + " not found");
        }
        riwayatPenghuniRepository.deleteById(id);
    }
}