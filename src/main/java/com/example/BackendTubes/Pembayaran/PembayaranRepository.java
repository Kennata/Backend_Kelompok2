package com.example.BackendTubes.Pembayaran;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PembayaranRepository extends JpaRepository<Pembayaran, Long> {
    List<Pembayaran> findAllByPenghuniId(Long penghuniId);
    
    void deleteByPenghuniId(Long penghuniId);
}
