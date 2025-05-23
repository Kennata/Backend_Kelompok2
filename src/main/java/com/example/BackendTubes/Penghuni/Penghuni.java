package com.example.BackendTubes.Penghuni;

import com.example.BackendTubes.Dokumen.Dokumen;
import com.example.BackendTubes.Pembayaran.Pembayaran;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "penghuni")
public class Penghuni {
    @Id
    @SequenceGenerator(
            name = "penghuni_sequence",
            sequenceName = "penghuni_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "penghuni_sequence"
    )
    private Long id;
    private String nama;
    private int usia;
    private String pekerjaan;
    private String nomorHp;
    private String kontakDarurat;
    private String jenisKendaraan;
    private String platKendaraan;

    @OneToMany(mappedBy = "penghuni")
    @JsonIgnore
    private List<Pembayaran> riwayatPembayaran;

    @OneToMany(mappedBy = "penghuni")
    @JsonIgnore
    private List<Dokumen> dokumenIdentitas;

    public Penghuni() {
    }

    public Penghuni(Long id, String nama, int usia, String pekerjaan, String nomorHp, String kontakDarurat, String jenisKendaraan, String platKendaraan, List<Pembayaran> riwayatPembayaran) {
        this.id = id;
        this.nama = nama;
        this.usia = usia;
        this.pekerjaan = pekerjaan;
        this.nomorHp = nomorHp;
        this.kontakDarurat = kontakDarurat;
        this.jenisKendaraan = jenisKendaraan;
        this.platKendaraan = platKendaraan;
        this.riwayatPembayaran = riwayatPembayaran;
    }

    public Penghuni(String nama, int usia, String pekerjaan, String nomorHp, String kontakDarurat, String jenisKendaraan, String platKendaraan, List<Pembayaran> riwayatPembayaran) {
        this.nama = nama;
        this.usia = usia;
        this.pekerjaan = pekerjaan;
        this.nomorHp = nomorHp;
        this.kontakDarurat = kontakDarurat;
        this.jenisKendaraan = jenisKendaraan;
        this.platKendaraan = platKendaraan;
        this.riwayatPembayaran = riwayatPembayaran;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getKontakDarurat() {
        return kontakDarurat;
    }

    public void setKontakDarurat(String kontakDarurat) {
        this.kontakDarurat = kontakDarurat;
    }

    public List<Pembayaran> getRiwayatPembayaran() {
        return riwayatPembayaran;
    }

    public void setRiwayatPembayaran(List<Pembayaran> riwayatPembayaran) {
        this.riwayatPembayaran = riwayatPembayaran;
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public String getPlatKendaraan() {
        return platKendaraan;
    }

    public void setPlatKendaraan(String platKendaraan) {
        this.platKendaraan = platKendaraan;
    }

    @Override
    public String toString() {
        return "Penghuni{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", usia=" + usia +
                ", pekerjaan='" + pekerjaan + '\'' +
                ", nomorHp='" + nomorHp + '\'' +
                ", kontakDarurat='" + kontakDarurat + '\'' +
                ", jenisKendaraan='" + jenisKendaraan + '\'' +
                ", platKendaraan='" + platKendaraan + '\'' +
                ", riwayatPembayaran=" + riwayatPembayaran +
                '}';
    }
}
