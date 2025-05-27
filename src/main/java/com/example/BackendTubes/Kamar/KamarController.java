/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Kamar;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author LENOVO
 */
@RestController
@RequestMapping("/api/kos/view")
public class KamarController {
    @Autowired
    private KamarService kamarService;
    
    @GetMapping("/kamar/{id}")
    public Map<String, Object> viewKamar(@PathVariable Long id){
        return kamarService.viewKamar(id);
    }
}
