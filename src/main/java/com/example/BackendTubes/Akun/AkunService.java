/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Akun;

/**
 *
 * @author LENOVO
 */
/*
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AkunService {

    private final AkunRepository akunRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AkunService(AkunRepository akunRepository) {
        this.akunRepository = akunRepository;
    }

    public Map<String, String> register(AkunDTO akunDTO, String role) {
        Optional<Akun> existingAkun = akunRepository.findByEmail(akunDTO.getEmail());
        Map<String, String> response = new HashMap<>();

        if (existingAkun.isPresent()) {
            response.put("message", "Email already exists!");
            return response;
        }

        Akun user = new Akun();
        user.setEmail(akunDTO.getEmail());
        user.setPassword(passwordEncoder.encode(akunDTO.getPassword()));
        user.setRole(role);
        akunRepository.save(user);

        response.put("message", "Register successful as " + role);
        return response;
    }

    public Map<String, String> login(AkunDTO akunDTO) {
        Map<String, String> response = new HashMap<>();
        Optional<Akun> akunOpt = akunRepository.findByEmail(akunDTO.getEmail());

        if (akunOpt.isPresent()) {
            Akun user = akunOpt.get();
            if (passwordEncoder.matches(akunDTO.getPassword(), user.getPassword())) {
                response.put("message", "Login successful as " + user.getRole());
                return response;
            }
        }

        response.put("message", "Invalid email or password!");
        return response;
    }
}

 */
