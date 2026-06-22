package com.example.demo.controller;

import com.example.demo.entity.Lapangan;
import com.example.demo.service.LapanganService;
import com.example.demo.service.JadwalService;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/jadwal")
public class JadwalController {

    @Autowired
    private LapanganService lapanganService;

    @Autowired
    private JadwalService jadwalService;

    @Autowired
    private BookingService bookingService;

    // HALAMAN JADWAL LAPANGAN
    @GetMapping("/{idLapangan}")
    public String viewJadwal(
            @PathVariable Integer idLapangan,
            @RequestParam(required = false) String tanggal,
            Model model
    ) {

        // ambil data lapangan
        Lapangan lapangan = lapanganService.getById(idLapangan);

        // default tanggal hari ini kalau tidak dipilih
        LocalDate tgl = (tanggal == null) ? LocalDate.now() : LocalDate.parse(tanggal);

        // ambil jadwal + booking
        model.addAttribute("lapangan", lapangan);
        model.addAttribute("daftarJadwal",
                jadwalService.getJadwalByLapanganAndTanggal(idLapangan, tgl));

        model.addAttribute("daftarBooking",
                bookingService.getBookingByLapanganAndTanggal(idLapangan, tgl));

        model.addAttribute("tanggal", tgl);

        return "jadwal"; 
        // pastikan file: templates/jadwal.html
    }

    // BOOKING SLOT
    @PostMapping("/booking")
    public String booking(
            @RequestParam Integer idLapangan,
            @RequestParam(required = false) Integer[] idJadwal
    ) {

        if (idJadwal != null) {
            bookingService.buatBooking(idLapangan, idJadwal);
        }

        return "redirect:/jadwal/" + idLapangan + "?success=true";
    }

    // RESET BOOKING
    @PostMapping("/reset")
    public String reset(
            @RequestParam Integer idLapangan,
            @RequestParam String tanggal
    ) {

        bookingService.resetBooking(idLapangan, LocalDate.parse(tanggal));

        return "redirect:/jadwal/" + idLapangan + "?success=reset";
    }
}