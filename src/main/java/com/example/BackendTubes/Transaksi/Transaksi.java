package com.example.BackendTubes.Transaksi;

import java.time.LocalDate;

import com.example.BackendTubes.Penghuni.Penghuni;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate tanggalPembayaran;
    private int nominal;

    @ManyToOne
    @JoinColumn(name = "penghuni_id")
    @JsonIgnore
    private Penghuni penghuni;
    
    public Transaksi() {
    }

    public Transaksi(int nominal, LocalDate tanggalPembayaran) {
        this.nominal = nominal;
        this.tanggalPembayaran = tanggalPembayaran;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setTanggalPembayaran(LocalDate tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public Penghuni getPenghuni() {
        return penghuni;
    }

    public void setPenghuni(Penghuni penghuni) {
        this.penghuni = penghuni;
    }
}
