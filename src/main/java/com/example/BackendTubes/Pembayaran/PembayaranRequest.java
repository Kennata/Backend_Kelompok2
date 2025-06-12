package com.example.BackendTubes.Pembayaran;

import java.time.LocalDate;

public class PembayaranRequest {
    private LocalDate tanggalBayar;
    private int nominalPembayaran;
    private String status;
    private Long penghuniId;

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

    public Long getPenghuniId() {
        return penghuniId;
    }

    public void setPenghuniId(Long penghuniId) {
        this.penghuniId = penghuniId;
    }

    public int getNominalPembayaran() {
        return nominalPembayaran;
    }

    public void setNominalPembayaran(int nominalPembayaran) {
        this.nominalPembayaran = nominalPembayaran;
    }
}
