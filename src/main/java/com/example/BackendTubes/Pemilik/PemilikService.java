/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Pemilik;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class PemilikService {
    private final PemilikRepository pemilikRepository;

    @Autowired
    public PemilikService(PemilikRepository pemilikRepository) {
        this.pemilikRepository = pemilikRepository;
    }

    public List<Pemilik> getPemilik() {
        return pemilikRepository.findAll();
    }

    public void addNewPemilik(Pemilik pemilik) {
        //update method ini saat kelas abstrak akun udah dibuat

        pemilikRepository.save(pemilik);
    }

    public void updatePemilik(Long id, Map<String, Object> update) {
        Pemilik pemilik = pemilikRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("pemilik with id " + id + " does not exist"));

        if (update.containsKey("nama")) {
            pemilik.setNama((String) update.get("nama"));
        }

        pemilikRepository.save(pemilik);
    }

    public Map<String, Object> tambahPemilik(PemilikDTO pemilikDTO) {
        Optional<Pemilik> existingPemilik = pemilikRepository.findByNama(pemilikDTO.getNama());
        Map<String, Object> response = new HashMap<>();
        if (existingPemilik.isPresent()) {
            response.put("message", "Pemilik Sudah Ada");
            return response;
        }
        Pemilik pemilik = new Pemilik();
        pemilik.setNama(pemilikDTO.getNama());
        pemilik.setEmail(pemilikDTO.getEmail());
        pemilik.setPassword(pemilikDTO.getPassword());
        pemilik.setRole(pemilikDTO.getRole());
        pemilikRepository.save(pemilik);
        response.put("message", "Pemilik Sudah Ditambahkan");
        return response;
    }

    public Pemilik findPemilikByNama(String nama) {
        Pemilik pemilik = pemilikRepository.findByNama(nama)
                .orElseThrow(() -> new IllegalStateException("pemilik with nama " + nama + " does not exist"));

        return pemilik;
    }
}
