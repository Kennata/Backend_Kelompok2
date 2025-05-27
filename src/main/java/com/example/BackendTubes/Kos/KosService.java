/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Kos;

import com.example.BackendTubes.Kamar.Kamar;
import com.example.BackendTubes.Kamar.KamarRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class KosService {

    private final KosRepository kosRepository;
    private KamarRepository kamarRepository;
    ArrayList<Object> data = new ArrayList<>();

    @Autowired
    public KosService(KosRepository kosRepository, KamarRepository kamarRepository) {
        this.kosRepository = kosRepository;
        this.kamarRepository = kamarRepository;
    }

    public Map<String, Object> addKos(KosDTO kosDTO) {
        Optional<Kos> existingKos = kosRepository.findByNamaKos(kosDTO.getNamaKos());
        Map<String, Object> response = new HashMap<>();

        if (existingKos.isPresent()) {
            response.put("message", "Nama kos sudah ada");
            return response;
        }

        Kos kos = new Kos();
        kos.setNamaKos(kosDTO.getNamaKos());
        kos.setJumlahKamar(kosDTO.getJumlahKamar());
        kos.setHarga(kosDTO.getHarga());
        kos.setAlamat(kosDTO.getAlamat());
        kos.setDeskripsi(kosDTO.getDeskripsi());
        kos.setTipeKos(kosDTO.getTipeKos());
        List<Kamar> dataKamar = new ArrayList<>();
        for (int i = 1; i <= kosDTO.getJumlahKamar(); i++) {
            Kamar kamar = new Kamar();
            kamar.setNoKamar(i);
            kamar.setStatus("Kosong");
            dataKamar.add(kamar);
            kamarRepository.save(kamar);
        }
        kos.setDataKamar(dataKamar);
        kosRepository.save(kos);
        response.put("message", kos.getNamaKos() + " telah berhasil ditambahkan");
        response.put("harga", kos.getHarga());
        return response;
    }

    public Map<String, Object> deleteKos(Long id) {
        Optional<Kos> existingId = kosRepository.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (existingId.isEmpty()) {
            response.put("message", "Kos tidak ditemukan");
            return response;
        }

        kosRepository.deleteById(id);
        response.put("message", "Kos dengan id " + id + " telah terhapus");
        response.put("iseng", data);
        return response;
    }

    public Map<String, Object> editKos(Long id, KosDTO kosDTO) {
        Optional<Kos> existingId = kosRepository.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (existingId.isEmpty()) {
            response.put("message", "Kos tidak ditemukan");
            return response;
        }

        Kos kos = existingId.get();
        kos.setNamaKos(kosDTO.getNamaKos());
        kos.setHarga(kosDTO.getHarga());
        kos.setDeskripsi(kosDTO.getDeskripsi());
        kosRepository.save(kos);

        response.put("message", "Kos sudah diupdate");
        return response;
    }

    public Map<String, Object> viewKos() {
        List<Kos> kos = kosRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        response.put("dataKos", kos);
        return response;
    }
}
