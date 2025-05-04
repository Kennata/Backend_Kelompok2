package com.example.BackendTubes.Penghuni;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenghuniRepository extends JpaRepository<Penghuni, Long> {
}
