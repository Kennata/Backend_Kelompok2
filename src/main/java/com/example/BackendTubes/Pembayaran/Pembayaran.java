package com.example.BackendTubes.Pembayaran;

import jakarta.persistence.*;

import java.time.LocalDate;

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
    private LocalDate tanggalBayar;
    private String status;

    @ManyToOne
    @JoinColumn(name = "penghuniId", referencedColumnName = "id")
    private Long penghuniId;
    public Pembayaran() {
    }

    public Pembayaran(Long id, LocalDate tanggalBayar, String status) {
        this.id = id;
        this.tanggalBayar = tanggalBayar;
        this.status = status;
    }

    public Pembayaran(LocalDate tanggalBayar, String status) {
        this.tanggalBayar = tanggalBayar;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(LocalDate tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
