/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Kamar;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BackendTubes.Kos.Kos;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.BackendTubes.Kos.KosRepository;

/**
 *
 * @author LENOVO
 */
@Service
public class KamarService {

    private final KosRepository kosRepository;

    @Autowired
    public KamarService(KosRepository kosRepository) {
        this.kosRepository = kosRepository;
    }

    public Map<String, Object> viewKamar(Long Id) {
        Optional<Kos> cekKos = kosRepository.findById(Id);
        Map<String, Object> response = new HashMap<>();
        if (cekKos.isEmpty()) {
            response.put("message", "Kos Tidak Ditemukan");
            return response;
        }
        Kos kos = cekKos.get();
        List<Kamar> daftarLama = kos.getDataKamar(); 
        List<KamarDTO> daftarBaru = daftarLama
            .stream()
            .map(Kamar -> new KamarDTO(Kamar.getNoKamar(),Kamar.getStatus()))
            .collect(Collectors.toList());
        response.put("dataKamar", daftarBaru);
        return response;
    }
}
