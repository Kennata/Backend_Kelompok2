/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Kamar;

import com.example.BackendTubes.Kos.Kos;

import jakarta.persistence.*;

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
    
}
