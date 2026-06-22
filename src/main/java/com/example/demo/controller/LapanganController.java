package com.example.demo.controller;

import com.example.demo.entity.Lapangan;
import com.example.demo.service.LapanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lapangan")
public class LapanganController {

    @Autowired
    private LapanganService lapanganService;

    @GetMapping
    public String daftarLapangan(Model model) {
        List<Lapangan> daftarLapangan = lapanganService.lihatDaftarLapangan();
        model.addAttribute("daftarLapangan", daftarLapangan);
        model.addAttribute("lapanganBaru", new Lapangan());
        return "daftarLapangan";
    }

    @PostMapping("/simpan")
    public String simpanLapangan(@ModelAttribute Lapangan lapangan) {
        if (lapangan.getIdLapangan() == null) {
            lapanganService.tambahLapangan(lapangan);
        } else {
            lapanganService.updateLapangan(lapangan.getIdLapangan(), lapangan);
        }
        return "redirect:/lapangan";
    }

    @GetMapping("/hapus/{id}")
    public String hapusLapangan(@PathVariable Integer id) {
        lapanganService.hapusLapangan(id);
        return "redirect:/lapangan";
    }
}