package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Jadwal;
import com.example.demo.entity.Member;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.JadwalRepository;
import com.example.demo.repository.MemberRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private JadwalRepository jadwalRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        return bookingOptional.orElse(null);
    }

    public List<Booking> getBookingByMember(Long memberId) {
        return bookingRepository.findByMember_IdOrderByTanggalDibuatDesc(memberId);
    }

    public List<Booking> getBookingByLapanganDanTanggal(Integer idLapangan, LocalDate tanggal) {
        return bookingRepository.findByJadwal_Lapangan_IdLapanganAndJadwal_Tanggal(idLapangan, tanggal);
    }

    // Sekarang butuh idJadwal DAN memberId
    public Booking buatBooking(Integer idJadwal, Long memberId) {

        Jadwal jadwal = jadwalRepository.findById(idJadwal).orElse(null);
        if (jadwal == null) {
            throw new RuntimeException("Jadwal tidak ditemukan.");
        }

        if (!jadwal.cekSlotKosong()) {
            throw new RuntimeException("Maaf, slot jadwal ini sudah dibooking.");
        }

        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            throw new RuntimeException("Member tidak ditemukan.");
        }

        jadwal.setIsAvailable(false);
        jadwalRepository.save(jadwal);

        Booking booking = new Booking();
        booking.setJadwal(jadwal);
        booking.setMember(member);
        booking.setTanggalDibuat(LocalDateTime.now());
        booking.setStatus("Dipesan");

        return bookingRepository.save(booking);
    }

    public void resetBookingByLapanganDanTanggal(Integer idLapangan, LocalDate tanggal) {

        List<Booking> bookingTerkait = bookingRepository
                .findByJadwal_Lapangan_IdLapanganAndJadwal_Tanggal(idLapangan, tanggal);

        for (Booking booking : bookingTerkait) {
            Jadwal jadwal = booking.getJadwal();
            jadwal.setIsAvailable(true);
            jadwalRepository.save(jadwal);
        }

        bookingRepository.deleteAll(bookingTerkait);
    }
}