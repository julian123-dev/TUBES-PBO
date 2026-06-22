package com.example.demo.controller;

import com.example.demo.entity.Harga;
import com.example.demo.entity.Paket;
import com.example.demo.service.HargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/harga")
public class HargaController {

    @Autowired
    private HargaService hargaService;

    // ===== HALAMAN DETAIL HARGA =====
    // Dipanggil dari landing page Rafael: /harga/detail?idLapangan=1
    @GetMapping("/detail")
    public String detailHarga(@RequestParam(name = "idLapangan", required = false) Integer idLapangan,
                               Model model) {

        // 1. Ambil data lapangan berdasarkan ID yang dikirim Rafael (untuk autofill/pre-select)
        if (idLapangan != null) {
            Optional<Harga> lapangan = hargaService.getLapanganById(idLapangan);
            if (lapangan.isPresent()) {
                model.addAttribute("lapangan", lapangan.get());
            } else {
                model.addAttribute("lapangan", null);
                model.addAttribute("errorMsg", "Lapangan tidak ditemukan.");
            }
        } else {
            model.addAttribute("lapangan", null);
            // Hapus pesan error bawaan lama agar tidak muncul text mengganggu saat pertama kali buka tanpa ID
        }

        /* * ========== PERUBAHAN DI SINI ==========
         * 2. Ambil SEMUA data lapangan untuk mengisi opsi Dropdown secara dinamis dari DB
         * (Pastikan method getAllLapangan() sudah kamu buat di HargaService.java ya!)
         */
        List<Harga> lapanganList = hargaService.getAllLapangan();
        model.addAttribute("lapanganList", lapanganList);

        // 3. Ambil semua paket aktif untuk ditampilkan sebagai rekomendasi
        List<Paket> paketList = hargaService.getPaketAktif();
        model.addAttribute("paketList", paketList);

        return "harga/harga";
    }
}