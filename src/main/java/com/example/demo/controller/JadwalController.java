package com.example.demo.controller;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Jadwal;
import com.example.demo.entity.Lapangan;
import com.example.demo.entity.Member;
import com.example.demo.service.BookingService;
import com.example.demo.service.JadwalService;
import com.example.demo.service.LapanganService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/jadwal")
public class JadwalController {

    @Autowired
    private JadwalService jadwalService;

    @Autowired
    private LapanganService lapanganService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/{idLapangan}")
    public String lihatJadwal(@PathVariable Integer idLapangan,
                              @RequestParam(required = false) String tanggal,
                              Model model) {

        Lapangan lapangan = lapanganService.lihatDetailLapangan(idLapangan)
                .orElseThrow(() -> new RuntimeException("Lapangan tidak ditemukan"));

        LocalDate tgl = (tanggal != null && !tanggal.isEmpty())
                ? LocalDate.parse(tanggal)
                : LocalDate.now();

        jadwalService.generateSlotHarian(lapangan, tgl);

        List<Jadwal> daftarJadwal =
                jadwalService.getJadwalByLapanganDanTanggal(lapangan, tgl);

        List<Booking> daftarBooking =
                bookingService.getBookingByLapanganDanTanggal(idLapangan, tgl);

        model.addAttribute("lapangan", lapangan);
        model.addAttribute("daftarJadwal", daftarJadwal);
        model.addAttribute("daftarBooking", daftarBooking);
        model.addAttribute("tanggal", tgl.toString());

        // ✅ FIX UTAMA DI SINI
        return "lapangan/daftarJadwal";
    }

    @PostMapping("/booking")
    public String prosesBooking(@RequestParam List<Integer> idJadwal,
                                @RequestParam Integer idLapangan,
                                HttpSession session) {

        Member member = (Member) session.getAttribute("member");

        if (member == null) {
            return "redirect:/login";
        }

        for (Integer id : idJadwal) {
            bookingService.buatBooking(id, member.getId());
        }

        return "redirect:/jadwal/" + idLapangan + "?success=true";
    }

    @PostMapping("/reset")
    public String resetBooking(@RequestParam Integer idLapangan,
                               @RequestParam String tanggal) {

        bookingService.resetBookingByLapanganDanTanggal(
                idLapangan,
                LocalDate.parse(tanggal)
        );

        return "redirect:/jadwal/" + idLapangan
                + "?tanggal=" + tanggal
                + "&success=reset";
    }
}