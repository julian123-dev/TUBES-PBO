package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Jadwal;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.JadwalRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private JadwalRepository jadwalRepository;

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

    // Membuat booking baru dari satu id Jadwal
    public Booking buatBooking(Integer idJadwal, Long memberId) {

        Jadwal jadwal = jadwalRepository.findById(idJadwal).orElse(null);

        if (jadwal == null) {
            throw new RuntimeException("Jadwal tidak ditemukan.");
        }

        if (!jadwal.cekSlotKosong()) {
            throw new RuntimeException("Maaf, slot jadwal ini sudah dibooking.");
        }

        jadwal.setIsAvailable(false);
        jadwalRepository.save(jadwal);

        Booking booking = new Booking();
        booking.setJadwal(jadwal);
        booking.setTanggalDibuat(LocalDateTime.now());
        booking.setStatus("Dipesan");

        return bookingRepository.save(booking);
    }

    // Reset booking untuk satu lapangan & tanggal tertentu
    public void resetBookingByLapanganDanTanggal(Integer idLapangan, LocalDate tanggal) {

        List<Jadwal> daftarJadwal = jadwalRepository.findByLapangan_IdLapanganAndTanggal(idLapangan, tanggal);

        for (Jadwal jadwal : daftarJadwal) {
            List<Booking> bookingTerkait = bookingRepository.findByJadwal_IdJadwal(jadwal.getIdJadwal());
            bookingRepository.deleteAll(bookingTerkait);

            jadwal.setIsAvailable(true);
            jadwalRepository.save(jadwal);
        }
    }
}