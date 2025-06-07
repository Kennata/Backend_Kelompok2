/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Pemilik;

import com.example.BackendTubes.Akun.Akun;
import com.example.BackendTubes.Kos.Kos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "pemilik")
public class Pemilik extends Akun {

    @Id
    @SequenceGenerator(
            name = "pemilik_sequence",
            sequenceName = "pemilik_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pemilik_sequence"
    )
    private Long id;
    private String nama;

    @OneToMany(mappedBy = "pemilik")
    @JsonIgnore
    private List<Kos> listKos;

//    @ManyToOne
//    @JoinColumn(name="noKamar")
//    private Kamar kamar;
    
    public Pemilik() {
    }

    public Pemilik(Long id, String nama, List<Kos> listKos) {
        this.id = id;
        this.nama = nama;
        this.listKos = listKos;
    }

    public Pemilik(String nama, List<Kos> listKos) {
        this.nama = nama;
        this.listKos = listKos;
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

    public List<Kos> getListKos() {
        return listKos;
    }

    public void setListKos(List<Kos> listKos) {
        this.listKos = listKos;
    }

    @Override
    public String toString() {
        return "Penghuni{"
                + "id=" + id
                + ", nama='" + nama + '\''
                + ", listKos=" + listKos
                + '}';
    }
}
