/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Kamar;

import com.example.BackendTubes.Kos.Kos;
import com.example.BackendTubes.Kos.KosRepository;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

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

    public Map<String, Object> viewKamar(Long id) {
        Optional<Kos> kosId = kosRepository.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (kosId.isEmpty()) {
            response.put("message", "Kos Tidak Ditemukan");
            return response;
        }
        Kos kos = kosId.get();
        response.put("dataKamar", kos.getDataKamar());
        return response;
    }

}
