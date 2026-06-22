package com.example.demo.controller;

import com.example.demo.entity.Paket;
import com.example.demo.service.PaketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/paket")
public class PaketController {

    @Autowired
    private PaketService paketService;

    // ===== HALAMAN DAFTAR PAKET AKTIF =====
    // Dipanggil dari halaman detail harga untuk menampilkan rekomendasi paket
    @GetMapping("/list")
    public String listPaket(Model model) {
        List<Paket> paketList = paketService.getPaketAktif();
        model.addAttribute("paketList", paketList);
        return "paket/list";
    }

    // ===== HALAMAN DETAIL PAKET =====
    // Dipanggil saat user klik salah satu paket untuk lihat detail
    @GetMapping("/detail")
    public String detailPaket(@RequestParam(name = "idPaket", required = false) Integer idPaket,
                               Model model) {
        if (idPaket != null) {
            Optional<Paket> paket = paketService.getPaketById(idPaket);
            if (paket.isPresent()) {
                model.addAttribute("paket", paket.get());
            } else {
                model.addAttribute("paket", null);
                model.addAttribute("errorMsg", "Paket tidak ditemukan.");
            }
        } else {
            model.addAttribute("paket", null);
        }

        // Tampilkan juga semua paket aktif sebagai rekomendasi lainnya
        List<Paket> paketList = paketService.getPaketAktif();
        model.addAttribute("paketList", paketList);

        return "paket/detail";
    }
}