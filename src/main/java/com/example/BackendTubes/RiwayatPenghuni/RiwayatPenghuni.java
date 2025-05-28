package com.example.BackendTubes.RiwayatPenghuni;

import com.example.BackendTubes.Kos.Kos;
import jakarta.persistence.*;

@Entity
@Table(name = "riwayat_penghuni")
public class RiwayatPenghuni {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "kos_id", referencedColumnName = "id")
    private Kos kos;
    
    private String tanggalMasuk;
    private String tanggalKeluar;

    // Add no-arg constructor for Hibernate
    public RiwayatPenghuni() {
    }

    public RiwayatPenghuni(String id, Kos kos, String tanggalMasuk, String tanggalKeluar) {
        this.id = id;
        this.kos = kos;
        this.tanggalMasuk = tanggalMasuk;
        this.tanggalKeluar = tanggalKeluar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Kos getKos() {
        return kos;
    }

    public void setKos(Kos kos) {
        this.kos = kos;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public String getTanggalKeluar() {
        return tanggalKeluar;
    }

    public void setTanggalKeluar(String tanggalKeluar) {
        this.tanggalKeluar = tanggalKeluar;
    }
    
    @Override
    public String toString() {
        return "RiwayatPenghuni{" +
                "id='" + id + '\'' +
                ", kos=" + kos +
                ", tanggalMasuk='" + tanggalMasuk + '\'' +
                ", tanggalKeluar='" + tanggalKeluar + '\'' +
                '}';
    }
}