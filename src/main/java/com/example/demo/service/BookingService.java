package com.example.demo.service;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Jadwal;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.JadwalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private JadwalRepository jadwalRepository;

    public Booking buatBooking(Integer idJadwal) {
        Jadwal jadwal = jadwalRepository.findById(idJadwal)
                .orElseThrow(() -> new RuntimeException("Jadwal tidak ditemukan"));

        if (!jadwal.cekSlotKosong()) {
            throw new RuntimeException("Jadwal sudah terpesan");
        }

        Booking booking = new Booking();
        booking.setMember(null);
        booking.setJadwal(jadwal);
        booking.setTanggalBooking(LocalDate.now());
        booking.setStatus("Pending");
        booking.hitungHarga();

        jadwal.setIsAvailable(false);
        jadwalRepository.save(jadwal);

        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingByJadwal(Integer idJadwal) {
        return bookingRepository.findByJadwal_IdJadwal(idJadwal);
    }

    public Optional<Booking> getById(Integer idBooking) {
        return bookingRepository.findById(idBooking);
    }

    public List<Booking> getBookingByLapanganDanTanggal(Integer idLapangan, LocalDate tanggal) {
        return bookingRepository.findByJadwal_Lapangan_IdLapanganAndJadwal_Tanggal(idLapangan, tanggal);
    }

    public void resetBookingByLapanganDanTanggal(Integer idLapangan, LocalDate tanggal) {
        List<Booking> daftarBooking = bookingRepository
                .findByJadwal_Lapangan_IdLapanganAndJadwal_Tanggal(idLapangan, tanggal);

        for (Booking booking : daftarBooking) {
            Jadwal jadwal = booking.getJadwal();
            jadwal.setIsAvailable(true);
            jadwalRepository.save(jadwal);
            bookingRepository.delete(booking);
        }
    }

    public void hapusBooking(Integer idBooking) {
        Booking booking = bookingRepository.findById(idBooking)
                .orElseThrow(() -> new RuntimeException("Booking tidak ditemukan"));

        Jadwal jadwal = booking.getJadwal();
        jadwal.setIsAvailable(true);
        jadwalRepository.save(jadwal);

        bookingRepository.deleteById(idBooking);
    }
}