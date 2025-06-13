package com.example.BackendTubes.Penghuni;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenghuniRepository extends JpaRepository<Penghuni, Long> {
    Optional<Penghuni> findByNama(String nama);

    Optional<Penghuni> findByEmail(String email);
}
