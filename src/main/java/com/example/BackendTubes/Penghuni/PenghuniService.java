package com.example.BackendTubes.Penghuni;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PenghuniService {

    private final PenghuniRepository penghuniRepository;

    @Autowired
    public PenghuniService(PenghuniRepository penghuniRepository) {
        this.penghuniRepository = penghuniRepository;
    }

    public List<Penghuni> getPenghuni(){
        return penghuniRepository.findAll();
    }

    public void addNewPenghuni(Penghuni penghuni){
        //update method ini saat kelas abstrak akun udah dibuat

        penghuniRepository.save(penghuni);
    }

    public void updatePenghuni(Long id, Map<String, Object> update){
        Penghuni penghuni=penghuniRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("penghuni with id "+id+" does not exist"));

        if(update.containsKey("nama")){
            penghuni.setNama((String) update.get("nama"));
        }else if(update.containsKey("usia")){
            penghuni.setUsia((Integer) update.get("usia"));
        }else if(update.containsKey("pekerjaan")){
            penghuni.setPekerjaan((String) update.get("pekerjaan"));
        }else if(update.containsKey("nomorHp")){
            penghuni.setNomorHp((String) update.get("nomorHp"));
        }else if(update.containsKey("kontakDarurat")){
            penghuni.setKontakDarurat((String) update.get("kontakDarurat"));
        }else if(update.containsKey("jenisKendaraan")){
            penghuni.setJenisKendaraan((String) update.get("jenisKendaraan"));
        }else if(update.containsKey("platKendaraan")){
            penghuni.setPlatKendaraan((String) update.get("platKendaraan"));
        }

        penghuniRepository.save(penghuni);
    }

    public Map<String, Object> tambahPenghuni(PenghuniDTO penghuniDTO){
        Optional<Penghuni> existingPenghuni = penghuniRepository.findByNama(penghuniDTO.getNama());
        Map<String, Object> response = new HashMap<>();
        if (existingPenghuni.isPresent()){
            response.put("message", "Penghuni Sudah Ada");
            return response;
        }
        Penghuni penghuni = new Penghuni();
        penghuni.setNama(penghuniDTO.getNama());
        penghuni.setEmail(penghuniDTO.getEmail());
        penghuni.setPassword(penghuniDTO.getPassword());
        penghuni.setRole(penghuniDTO.getRole());
        penghuni.setUsia(penghuniDTO.getUsia());
        penghuni.setNomorHp(penghuniDTO.getNomorHp());
        penghuni.setPekerjaan(penghuniDTO.getPekerjaan());
        penghuni.setKontakDarurat(penghuniDTO.getKontakDarurat());
        penghuni.setJenisKendaraan(penghuniDTO.getJenisKendaraan());
        penghuni.setPlatKendaraan(penghuniDTO.getPlatKendaraan());
        penghuniRepository.save(penghuni);
        response.put("message", "Penghuni Sudah Ditambahkan");
        return response;
    }

    public Penghuni findPenghuniByNama(String nama){
        Penghuni penghuni=penghuniRepository.findByNama(nama)
                .orElseThrow(() -> new IllegalStateException("penghuni with nama "+nama+" does not exist"));

        return penghuni;
    }
}
