/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Akun;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author LENOVO
 */
@RestController
@RequestMapping("/api/akun")
public class AkunContoller {

    @Autowired
    private AkunService akunService;

    @PostMapping("/register/penghuni")
    public Map<String, String> registerPenghuni(@RequestBody AkunDTO akunDTO) {
        return akunService.register(akunDTO, "PENGHUNI");
    }

    @PostMapping("/register/pemilik")
    public Map<String, String> registerPemilik(@RequestBody AkunDTO akunDTO) {
        return akunService.register(akunDTO, "PEMILIK");
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AkunDTO akunDTO) {
        return akunService.login(akunDTO);
    }

}
