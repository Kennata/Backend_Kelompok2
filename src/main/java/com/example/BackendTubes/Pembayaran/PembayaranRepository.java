package com.example.BackendTubes.Pembayaran;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PembayaranRepository extends JpaRepository<Pembayaran, Long> {
    List<Pembayaran> findAllByPenghuniId(Long penghuniId);

    void deleteByPenghuniId(Long penghuniId);
}
