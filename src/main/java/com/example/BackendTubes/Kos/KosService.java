/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Kos;

import com.example.BackendTubes.Kamar.Kamar;
import com.example.BackendTubes.Kamar.KamarDTO;
import com.example.BackendTubes.Kamar.KamarRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class KosService {

    private final KosRepository kosRepository;
    private final KamarRepository kamarRepository;
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
        List<Kos> kosList = kosRepository.findAll();
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> hasil = new ArrayList<>();

        for (Kos kos : kosList) {
            Map<String, Object> kosMap = new LinkedHashMap<>();
            kosMap.put("id", kos.getId());
            kosMap.put("namaKos", kos.getNamaKos());
            kosMap.put("alamat", kos.getAlamat());
            kosMap.put("jumlahKamar", kos.getJumlahKamar());
            kosMap.put("deskripsi", kos.getDeskripsi());
            kosMap.put("tipeKos", kos.getTipeKos());
            kosMap.put("harga", kos.getHarga());
            List<KamarDTO> kamarDTOList = kos.getDataKamar()
                .stream()
                .map(kamar -> new KamarDTO(kamar.getNoKamar(), kamar.getStatus()))
                .collect(Collectors.toList());

            kosMap.put("dataKamar", kamarDTOList);
            hasil.add(kosMap);
        }
        response.put("dataKos", hasil);
        return response;
    }
}
