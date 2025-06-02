/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Kamar;

import java.util.List;

import com.example.BackendTubes.Kos.Kos;
import com.example.BackendTubes.Penghuni.Penghuni;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name="Kamar")
public class Kamar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int noKamar;
    private String status;
    @ManyToOne
    @JoinColumn(name="kosId")
    private Kos kos;
    @OneToMany
    @JoinColumn(name="noKamar", referencedColumnName="noKamar")
    private List<Penghuni> dataPenghuni;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNoKamar() {
        return noKamar;
    }

    public void setNoKamar(int noKamar) {
        this.noKamar = noKamar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Kos getKos() {
        return kos;
    }

    public void setKos(Kos kos) {
        this.kos = kos;
    }

    public List<Penghuni> getDataPenghuni() {
        return dataPenghuni;
    }

    public void setDataPenghuni(List<Penghuni> dataPenghuni) {
        this.dataPenghuni = dataPenghuni;
    }
    
}
