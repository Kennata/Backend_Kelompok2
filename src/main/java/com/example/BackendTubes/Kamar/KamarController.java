/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Kamar;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@RestController
@RequestMapping("/api/kos/{kosId}")
public class KamarController {
    @Autowired
    private KamarService kamarService;
    
    @GetMapping
    public Map<String, Object> viewKamar(@PathVariable Long kosId){
        return kamarService.viewKamar(kosId);
    }

    @PostMapping("/{noKamar}/penghuni/{penghuniId}")
    public Map<String, Object> assignPenghuni(@PathVariable Long kosId, @PathVariable int noKamar, @PathVariable Long penghuniId){
        return kamarService.assignPenghuni(kosId, noKamar, penghuniId);
    }

    @GetMapping("/{noKamar}/penghuni")
    public Map<String, Object> viewPenghuni(@PathVariable Long kosId, @PathVariable int noKamar){
        return kamarService.viewPenghuni(kosId, noKamar);
    }

    @PostMapping("/{noKamar}/kosong")
    public Map<String, Object> kosongKamar(@PathVariable Long kosId, @PathVariable int noKamar){
        return kamarService.kosongKamar(kosId, noKamar);
    }
}
