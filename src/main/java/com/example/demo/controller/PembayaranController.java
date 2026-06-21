package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Pembayaran;
import com.example.demo.service.HistoryService;
import com.example.demo.service.PembayaranService;

@Controller
public class PembayaranController {

    @Autowired
    private PembayaranService pembayaranService;

    @Autowired
    private HistoryService historyService;

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
                "listPembayaran",
                pembayaranService.getAllPembayaran());

        model.addAttribute(
                "listHistory",
                historyService.getAllHistory());

        return "index";
    }

    @GetMapping("/tambah")
    public String tambah(Model model) {

        model.addAttribute(
                "pembayaran",
                new Pembayaran());

        return "form";
    }

    @PostMapping("/simpan")
    public String simpan(
            @ModelAttribute Pembayaran pembayaran,
            Model model) {

        Pembayaran hasil = pembayaranService.simpanPembayaran(pembayaran);

        model.addAttribute("pembayaran", hasil);

        return "struk";
    }

    @GetMapping("/hapus/{id}")
    public String hapus(@PathVariable Long id) {

        pembayaranService.hapusPembayaran(id);

        return "redirect:/";
    }
}