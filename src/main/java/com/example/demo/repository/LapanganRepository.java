package com.example.demo.repository;

import com.example.demo.entity.Lapangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LapanganRepository extends JpaRepository<Lapangan, Integer> {

    List<Lapangan> findByJenisIgnoreCase(String jenis);

    List<Lapangan> findByStatusIgnoreCase(String status);

    List<Lapangan> findByNamaContainingIgnoreCase(String keyword);
}