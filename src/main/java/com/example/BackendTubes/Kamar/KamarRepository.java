/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.BackendTubes.Kamar;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author LENOVO
 */
public interface KamarRepository extends JpaRepository<Kamar, Long>{
    Optional<Kamar> findByKosIdAndNoKamar(Long kosId,int noKamar);
}
