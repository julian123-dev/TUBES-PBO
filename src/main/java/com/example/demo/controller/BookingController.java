package com.example.demo.controller;

import com.example.demo.entity.Booking;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String daftarBooking(Model model) {
        List<Booking> daftarBooking = bookingService.getBookingByLapanganDanTanggal(null, null);
        model.addAttribute("daftarBooking", daftarBooking);
        return "Lapangan/daftarBooking";
    }
}