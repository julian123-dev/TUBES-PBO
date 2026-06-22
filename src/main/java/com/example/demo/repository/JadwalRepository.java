package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Jadwal;
import com.example.demo.entity.Lapangan;

@Repository
public interface JadwalRepository extends JpaRepository<Jadwal, Integer> {

    // Sesuai pemanggilan di JadwalService: pakai objek Lapangan langsung, bukan Integer id
    List<Jadwal> findByLapanganAndTanggal(Lapangan lapangan, LocalDate tanggal);
}