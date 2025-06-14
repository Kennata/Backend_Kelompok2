package com.example.BackendTubes.Penghuni;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BackendTubes.Kamar.Kamar;
import com.example.BackendTubes.Kos.Kos;
import com.example.BackendTubes.Transaksi.TransaksiService;

@Service
public class PenghuniService {

    private final PenghuniRepository penghuniRepository;
    private final TransaksiService transaksiService;

    @Autowired
    public PenghuniService(PenghuniRepository penghuniRepository, TransaksiService transaksiService) {
        this.penghuniRepository = penghuniRepository;
        this.transaksiService = transaksiService;
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
        Optional<Penghuni> existingPenghuni = penghuniRepository.findByNamaOrEmail(penghuniDTO.getNama(),penghuniDTO.getEmail());
        Map<String, Object> response = new HashMap<>();
        if (existingPenghuni.isPresent()){
            response.put("message", "Penghuni Sudah Ada");
            return response;
        }
        Penghuni penghuni = new Penghuni();
        penghuni.setNama(penghuniDTO.getNama());
        penghuni.setJenisKelamin(penghuniDTO.getJenisKelamin());
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

    public Map<String, Object> login(PenghuniDTO penghuniDTO) {
        Optional<Penghuni> existingEmail = penghuniRepository.findByEmail(penghuniDTO.getEmail());
        Map<String, Object> response = new HashMap<>();
        if (existingEmail.isEmpty()){
            response.put("message","Email tidak ditemukan");
            return response;
        }
        response.put("message","Login berhasil");
        return response;
    }

    public Map<String, Object> viewKos(String nama){
        Optional<Penghuni> existingPenghuni = penghuniRepository.findByNama(nama);
        Map<String, Object> response = new LinkedHashMap<>();
        if (existingPenghuni.isEmpty()){
            response.put("message","Penghuni tidak terdaftar");
            return response;
        } else if (existingPenghuni.get().getKamar() == null){
            response.put("message","Penghuni tidak terdaftar");
            return response;
        }
        Kamar kamar = existingPenghuni.get().getKamar();
        Kos kos = kamar.getKos();
        response.put("message", "Penghuni terdaftar");
        response.put("id", kos.getId());
        response.put("namaKos", kos.getNamaKos());
        response.put("alamat", kos.getAlamat());
        response.put("jumlahKamar", kos.getJumlahKamar());
        response.put("deskripsi", kos.getDeskripsi());
        response.put("tipeKos", kos.getTipeKos());
        response.put("harga", kos.getHarga());
        response.put("dataKamar", kamar);
        response.put("riwayatPembayaran", existingPenghuni.get().getRiwayatPembayaran());
        Map<String, Object> mapTransaksi = transaksiService.viewTransaksi(existingPenghuni.get().getId());
        response.putAll(mapTransaksi);
        return response;
    }
}
