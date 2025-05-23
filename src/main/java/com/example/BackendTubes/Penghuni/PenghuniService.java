package com.example.BackendTubes.Penghuni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

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
    }
}
