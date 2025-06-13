package com.example.BackendTubes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TanggalCounter {
    private LocalDate tanggalSekarang = LocalDate.now();
    private LocalDate tanggalDepan = tanggalSekarang.plusYears(1);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    public LocalDate getTanggalSekarang() {
        return tanggalSekarang;
    }
    public void setTanggalSekarang(String Tanggal) {
        String[] parts = Tanggal.split(" - ");
        this.tanggalSekarang = LocalDate.parse(parts[0],formatter);
    }
    public LocalDate getTanggalDepan() {
        return tanggalDepan;
    }
    public void setTanggalDepan(String Tanggal) {
        String[] parts = Tanggal.split(" - ");
        this.tanggalDepan = LocalDate.parse(parts[1],formatter);
    }
    
    @Override
    public String toString(){
        return tanggalSekarang +" - "+tanggalDepan;
    }
}
