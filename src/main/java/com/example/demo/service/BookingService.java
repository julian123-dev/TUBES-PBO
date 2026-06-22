package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Booking;
import com.example.demo.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Daftar semua jam operasional yang mungkin dibooking (08:00 - 21:00)
    private List<String> getSemuaSlotJam() {
        List<String> slot = new ArrayList<>();
        for (int jam = 8; jam <= 21; jam++) {
            slot.add(String.format("%02d:00", jam));
        }
        return slot;
    }

    // Ambil semua data booking
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    // Ambil booking berdasarkan id
    public Booking getBookingById(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        return bookingOptional.orElse(null);
    }

    // Ambil riwayat booking milik satu member, terbaru di atas
    public List<Booking> getBookingByMember(Long memberId) {
        return bookingRepository.findByMember_IdOrderByTanggalBookingDesc(memberId);
    }

    // Ambil semua booking untuk satu lapangan pada tanggal tertentu
    public List<Booking> getBookingByLapanganDanTanggal(Integer idLapangan, LocalDate tanggal) {
        return bookingRepository.findByLapangan_IdLapanganAndTanggalBooking(idLapangan, tanggal);
    }

    // Cek slot jam mana yang masih kosong, untuk ditampilkan di halaman jadwal
    public List<String> getSlotTersedia(Integer idLapangan, LocalDate tanggal) {

        List<String> semuaSlot = getSemuaSlotJam();

        List<Booking> bookingHariItu = getBookingByLapanganDanTanggal(idLapangan, tanggal);

        List<String> slotTerpakai = new ArrayList<>();
        for (Booking booking : bookingHariItu) {
            slotTerpakai.add(booking.getJamMulai().toString().substring(0, 5));
        }

        List<String> slotTersedia = new ArrayList<>();
        for (String slot : semuaSlot) {
            if (!slotTerpakai.contains(slot)) {
                slotTersedia.add(slot);
            }
        }

        return slotTersedia;
    }

    // Validasi: cek apakah slot jam tertentu sudah dibooking atau belum
    public boolean isSlotSudahDibooking(Integer idLapangan, LocalDate tanggal, LocalTime jamMulai) {
        List<Booking> bookingHariItu = getBookingByLapanganDanTanggal(idLapangan, tanggal);

        for (Booking booking : bookingHariItu) {
            if (booking.getJamMulai().equals(jamMulai)) {
                return true; // sudah ada yang booking di jam ini
            }
        }
        return false; // masih kosong
    }

    // Membuat booking baru, dengan validasi slot sebelum disimpan
    public Booking buatBooking(Booking booking) {

        boolean sudahAda = isSlotSudahDibooking(
                booking.getLapangan().getIdLapangan(),
                booking.getTanggalBooking(),
                booking.getJamMulai()
        );

        if (sudahAda) {
            throw new RuntimeException("Maaf, slot jam ini sudah dibooking. Silakan pilih jadwal lain.");
        }

        booking.setStatus("Dipesan");
        return bookingRepository.save(booking);
    }

    // Membatalkan booking
    public void batalkanBooking(Long id) {
        Booking booking = getBookingById(id);
        if (booking != null) {
            booking.setStatus("Dibatalkan");
            bookingRepository.save(booking);
        }
    }
}