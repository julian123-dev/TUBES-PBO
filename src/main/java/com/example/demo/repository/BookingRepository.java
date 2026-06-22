package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Cari booking berdasarkan id member, urut dari tanggal terbaru
    List<Booking> findByMember_IdOrderByTanggalBookingDesc(Long memberId);

    // Cari booking berdasarkan lapangan & tanggal (buat cek jadwal kosong/penuh)
    List<Booking> findByLapangan_IdLapanganAndTanggalBooking(Integer idLapangan, LocalDate tanggal);
}