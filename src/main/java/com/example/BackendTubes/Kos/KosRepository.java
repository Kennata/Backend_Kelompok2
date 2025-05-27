/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.BackendTubes.Kos;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author LENOVO
 */
public interface KosRepository extends JpaRepository<Kos, Long>{
    Optional<Kos> findByNamaKos(String namaKos);

    public Optional<Kos> getKosById(Long id);
}
