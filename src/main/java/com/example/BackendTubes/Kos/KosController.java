/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Kos;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author LENOVO
 */
@RestController
@RequestMapping("/api/kos")
public class KosController {
    
    @Autowired
    private KosService kosService;
    
    @PostMapping("/tambah")
    public Map<String, Object> addKos(@RequestBody KosDTO kosDTO){
        return kosService.addKos(kosDTO);
    }
    
    @DeleteMapping("/hapus/{id}")
    public Map<String, Object> deleteKos(@PathVariable Long id){
        return kosService.deleteKos(id);
    }
    
    @PostMapping("/edit/{id}")
    public Map<String, Object> editKos(@PathVariable Long id, @RequestBody KosDTO kosDTO){
        return kosService.editKos(id,kosDTO);
    }
    
    @GetMapping("/view")
    public Map<String, Object> viewKos(){
        return kosService.viewKos();
    }

    @GetMapping("/detail/{id}")
    public Map<String, Object> detailKos(@PathVariable Long id){
        return kosService.detailKos(id);
    }
}
