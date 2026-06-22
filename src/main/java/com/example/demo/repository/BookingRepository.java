package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByMember_IdOrderByTanggalDibuatDesc(Long memberId);

    List<Booking> findByJadwal_IdJadwal(Integer idJadwal);

    // Cari booking lewat relasi Booking -> Jadwal -> Lapangan, dan Booking -> Jadwal -> tanggal
    List<Booking> findByJadwal_Lapangan_IdLapanganAndJadwal_Tanggal(Integer idLapangan, LocalDate tanggal);
}