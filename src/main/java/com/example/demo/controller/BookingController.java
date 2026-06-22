package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Member;
import com.example.demo.service.BookingService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/booking")
    public String prosesBooking(@RequestParam List<Integer> idJadwal,
                                 @RequestParam Integer idLapangan,
                                 HttpSession session) {

        Member member = (Member) session.getAttribute("member");

        for (Integer id : idJadwal) {
            bookingService.buatBooking(id, member.getId());
        }
        return "redirect:/jadwal/" + idLapangan + "?success=true";
    }

    @PostMapping("/reset")
    public String resetBooking(@RequestParam Integer idLapangan,
                                @RequestParam String tanggal) {
        bookingService.resetBookingByLapanganDanTanggal(idLapangan, LocalDate.parse(tanggal));
        return "redirect:/jadwal/" + idLapangan + "?tanggal=" + tanggal + "&success=reset";
    }
}