package com.example.demo.repository;

import com.example.demo.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findByMember_IdMember(Integer idMember);

    List<Booking> findByStatusIgnoreCase(String status);

    List<Booking> findByJadwal_IdJadwal(Integer idJadwal);

    List<Booking> findByMember_IdMemberOrderByTanggalBookingDesc(Integer idMember);

    List<Booking> findByJadwal_Lapangan_IdLapanganAndJadwal_Tanggal(Integer idLapangan, LocalDate tanggal);
}