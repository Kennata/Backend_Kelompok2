package com.example.BackendTubes.Transaksi;

import java.time.LocalDate;

public class TransaksiDTO {
    private LocalDate tanggalPembayaran;
    private int nominal;

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public LocalDate getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setTanggalPembayaran(LocalDate tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }
}
