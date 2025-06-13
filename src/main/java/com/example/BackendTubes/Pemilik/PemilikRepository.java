/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.BackendTubes.Pemilik;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LENOVO
 */
@Repository
public interface PemilikRepository extends JpaRepository<Pemilik, Long> {
    Optional<Pemilik> findByNama(String nama);

    Optional<Pemilik> findByEmail(String email);

    Optional<Pemilik> findByNamaOrEmail(String nama, String email);
}
