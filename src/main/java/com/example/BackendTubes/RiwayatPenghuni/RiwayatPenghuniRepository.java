package com.example.BackendTubes.RiwayatPenghuni;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiwayatPenghuniRepository extends JpaRepository<RiwayatPenghuni, String>{
    
}
