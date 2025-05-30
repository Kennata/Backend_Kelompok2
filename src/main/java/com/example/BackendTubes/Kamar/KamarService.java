/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Kamar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BackendTubes.Kos.Kos;
import com.example.BackendTubes.Kos.KosRepository;
import com.example.BackendTubes.Penghuni.Penghuni;
import com.example.BackendTubes.Penghuni.PenghuniRepository;

/**
 *
 * @author LENOVO
 */
@Service
public class KamarService {

    private final KosRepository kosRepository;
    private final KamarRepository kamarRepository;
    private final PenghuniRepository penghuniRepository;

    @Autowired
    public KamarService(KamarRepository kamarRepository, KosRepository kosRepository, PenghuniRepository penghuniRepository) {
        this.kamarRepository = kamarRepository;
        this.kosRepository = kosRepository;
        this.penghuniRepository = penghuniRepository;
    }

    public Map<String, Object> viewKamar(Long Id) {
        Optional<Kos> cekKos = kosRepository.findById(Id);
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> hasil = new ArrayList<>();
        if (cekKos.isEmpty()) {
            response.put("message", "Kos Tidak Ditemukan");
            return response;
        }
        Kos kos = cekKos.get();
        List<Kamar> dataKamar = kos.getDataKamar(); 
        for (Kamar k : dataKamar){
            Map<String, Object> kamarMap = new LinkedHashMap<>();
            kamarMap.put("noKamar", k.getNoKamar());
            kamarMap.put("status", k.getStatus());
            Map<String, Object> penghuniMap = viewPenghuni(Id, k.getNoKamar());
            kamarMap.putAll(penghuniMap);
            hasil.add(kamarMap);
        }
        response.put("dataKamar", hasil);
        return response;
    }

    public Map<String, Object> assignPenghuni(Long id, int noKamar, Long penghuniId){
        Optional<Kamar> cekKamar = kamarRepository.findByKosIdAndNoKamar(id,noKamar);
        Penghuni p = penghuniRepository.getReferenceById(penghuniId);
        Map<String, Object> response = new HashMap<>();
        if (cekKamar.isEmpty()) {
            response.put("message", "Kamar Tidak Ditemukan");
            return response;
        }
        Kamar kamar = cekKamar.get();
        if (kamar.getStatus().equals("Terisi")){
            response.put("message", "Kamar Sudah Terisi");
            return response;
        }
        kamar.setStatus("Terisi");
        List<Penghuni> dataPenghuni = kamar.getDataPenghuni();
        dataPenghuni.add(p);
        kamar.setDataPenghuni(dataPenghuni);
        kamarRepository.save(kamar);
        response.put("message", "Penghuni Sudah di Assign");
        return response;
    }

    public Map<String, Object> viewPenghuni(Long id, int noKamar){
        Optional<Kamar> cekKamar = kamarRepository.findByKosIdAndNoKamar(id,noKamar);
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> hasil = new ArrayList<>();
        if (cekKamar.isEmpty()) {
            response.put("message", "Kos Tidak Ditemukan");
            return response;
        }
        Kamar kamar = cekKamar.get();
        List<Penghuni> dataPenghuni = kamar.getDataPenghuni();
        for (Penghuni p : dataPenghuni){
            Map<String, Object> kamarMap = new LinkedHashMap<>();
            kamarMap.put("id", p.getId());
            kamarMap.put("nama", p.getNama());
            kamarMap.put("usia", p.getUsia());
            kamarMap.put("nomorHp", p.getNomorHp());
            kamarMap.put("pekerjaan", p.getPekerjaan());
            kamarMap.put("kontakDarurat", p.getKontakDarurat());
            kamarMap.put("jenisKendaraan", p.getJenisKendaraan());
            kamarMap.put( "platKendaraan", p.getPlatKendaraan());
            hasil.add(kamarMap);
        }
        response.put("dataPenghuni", hasil);
        return response;
    }

    public Map<String, Object> kosongKamar(Long id, int noKamar){
        Optional<Kamar> cekKamar = kamarRepository.findByKosIdAndNoKamar(id,noKamar);
        Map<String, Object> response = new HashMap<>();
        if (cekKamar.isEmpty()) {
            response.put("message", "Kamar Tidak Ditemukan");
            return response;
        }
        Kamar kamar = cekKamar.get();
        if (kamar.getStatus().equals("Kosong")){
            response.put("message", "Kamar Sudah Kosong");
            return response;
        }
        kamar.setStatus("Kosong");
        List<Penghuni> dataPenghuni = kamar.getDataPenghuni();
        dataPenghuni.clear();
        kamarRepository.save(kamar);
        response.put("message", "Kamar Berhasil Dikosongkan");
        return response;
    }
}
