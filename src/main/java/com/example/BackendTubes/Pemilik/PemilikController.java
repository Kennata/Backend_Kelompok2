/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Pemilik;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@RestController
@RequestMapping(path = "api/pemilik")
public class PemilikController {
    private final PemilikService pemilikService;

    @Autowired
    public PemilikController(PemilikService pemilikService) {
        this.pemilikService = pemilikService;
    }

    @GetMapping
    public List<Pemilik> getPemilik() {
        return pemilikService.getPemilik();
    }

    @PostMapping
    public void addNewPemilik(@RequestBody Pemilik pemilik) {
        pemilikService.addNewPemilik(pemilik);
    }

    @PatchMapping(path = "{id}")
    public void updatePemilik(@PathVariable("id") Long id, @RequestBody Map<String, Object> update) {
        pemilikService.updatePemilik(id, update);
    }

    @PostMapping(path = "/tambah")
    public Map<String, Object> tambahPemilik(@RequestBody PemilikDTO pemilikDTO) {
        return pemilikService.tambahPemilik(pemilikDTO);
    }

    @GetMapping(path = "{nama}")
    public Pemilik getPemilikByNama(@PathVariable("nama") String nama) {
        return pemilikService.findPemilikByNama(nama);
    }
}
