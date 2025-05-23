package com.example.BackendTubes.Dokumen;

import com.example.BackendTubes.Penghuni.Penghuni;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class DokumenRequest {

    private String jenis;
    private String namaFile;
    private LocalDate tanggalUpload;
    private Long penghuniId;
    private MultipartFile file; // untuk PDF

    public DokumenRequest() {}

    public DokumenRequest(String jenis, String namaFile, Long penghuniId, MultipartFile file, LocalDate tanggalUpload) {
        this.jenis = jenis;
        this.namaFile = namaFile;
        this.penghuniId = penghuniId;
        this.file = file;
        this.tanggalUpload= tanggalUpload;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    public Long getPenghuniId() {
        return penghuniId;
    }

    public void setPenghuniId(Long penghuniId) {
        this.penghuniId = penghuniId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public LocalDate getTanggalUpload() {
        return tanggalUpload;
    }

    public void setTanggalUpload(LocalDate tanggalUpload) {
        this.tanggalUpload = tanggalUpload;
    }
}
