package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Jadwal;

@Repository
public interface JadwalRepository extends JpaRepository<Jadwal, Integer> {

    List<Jadwal> findByLapangan_IdLapanganAndTanggal(Integer idLapangan, LocalDate tanggal);
}