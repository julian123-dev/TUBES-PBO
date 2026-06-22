package com.example.demo.repository;

import com.example.demo.entity.Paket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaketRepository extends JpaRepository<Paket, Integer> {

    // Ambil semua paket yang aktif saja
    List<Paket> findByStatusTrue();
}