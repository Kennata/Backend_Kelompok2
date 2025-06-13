package com.example.BackendTubes.Pembayaran;

import java.time.LocalDate;

import com.example.BackendTubes.Penghuni.Penghuni;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "pembayaran")
public class Pembayaran {
    @Id
    @SequenceGenerator(
            name = "pembayaran_sequence",
            sequenceName = "pembayaran_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pembayaran_sequence"
    )
    private Long id;
    private LocalDate tanggalAwal;
    private LocalDate tanggalAkhir;
    private int nominalPembayaran;
    private String status;

    @ManyToOne
    @JoinColumn(name = "penghuniId", referencedColumnName = "id")
    @JsonIgnore
    private Penghuni penghuni;

    public Pembayaran() {
    }
    
    public Pembayaran(Long id, LocalDate tanggalAwal, LocalDate tanggalAkhir, int nominalPembayaran, String status,
            Penghuni penghuni) {
        this.id = id;
        this.tanggalAwal = tanggalAwal;
        this.tanggalAkhir = tanggalAkhir;
        this.nominalPembayaran = nominalPembayaran;
        this.status = status;
        this.penghuni = penghuni;
    }

    public Pembayaran(LocalDate tanggalAwal, LocalDate tanggalAkhir, int nominalPembayaran, String status,
            Penghuni penghuni) {
        this.tanggalAwal = tanggalAwal;
        this.tanggalAkhir = tanggalAkhir;
        this.nominalPembayaran = nominalPembayaran;
        this.status = status;
        this.penghuni = penghuni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public LocalDate getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(LocalDate tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public LocalDate getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(LocalDate tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Penghuni getPenghuni() {
        return penghuni;
    }

    public void setPenghuni(Penghuni penghuni) {
        this.penghuni = penghuni;
    }

    @Override
    public String toString() {
        return "Pembayaran{"
                + "id=" + id
                + ", tanggalBayar=" + tanggalAkhir
                + ", nominalPembayaran=" + nominalPembayaran
                + ", status='" + status + '\''
                + ", penghuni=" + penghuni
                + '}';
    }

    public int getNominalPembayaran() {
        return nominalPembayaran;
    }

    public void setNominalPembayaran(int nominalPembayaran) {
        this.nominalPembayaran = nominalPembayaran;
    }
}
