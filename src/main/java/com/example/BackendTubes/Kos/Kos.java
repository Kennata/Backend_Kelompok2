/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Kos;

import com.example.BackendTubes.Kamar.Kamar;
import com.example.BackendTubes.Pemilik.Pemilik;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "kost")
public class Kos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namaKos;
    private String alamat;
    private int jumlahKamar;
    private String deskripsi;
    private String tipeKos;
    private int harga;
    @OneToMany
    @JoinColumn(name = "kosId", referencedColumnName = "id")
    @JsonIgnore // Tambahkan ini juga kalau kamu kirim Kos langsung
    private List<Kamar> dataKamar;

    @ManyToOne
    @JoinColumn(name = "pemilikId", referencedColumnName = "id")
    private Pemilik pemilik;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaKos() {
        return namaKos;
    }

    public void setNamaKos(String namaKos) {
        this.namaKos = namaKos;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getJumlahKamar() {
        return jumlahKamar;
    }

    public void setJumlahKamar(int jumlahKamar) {
        this.jumlahKamar = jumlahKamar;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getTipeKos() {
        return tipeKos;
    }

    public void setTipeKos(String tipeKos) {
        this.tipeKos = tipeKos;
    }

    public List<Kamar> getDataKamar() {
        return dataKamar;
    }

    public void setDataKamar(List<Kamar> dataKamar) {
        this.dataKamar = dataKamar;
    }

}
