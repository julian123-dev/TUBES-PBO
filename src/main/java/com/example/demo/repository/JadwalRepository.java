package com.example.demo.repository;

import com.example.demo.entity.Jadwal;
import com.example.demo.entity.Lapangan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JadwalRepository extends JpaRepository<Jadwal, Integer> {

    List<Jadwal> findByLapanganAndTanggal(Lapangan lapangan, LocalDate tanggal);
}