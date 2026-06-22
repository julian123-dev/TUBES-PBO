package com.example.demo.controller;

import com.example.demo.entity.Harga;
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

    @GetMapping("/detail")
    public String detailHarga(@RequestParam(name = "idLapangan", required = false) Integer idLapangan,
                               Model model) {

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
        }

        List<Harga> lapanganList = hargaService.getAllLapangan();
        model.addAttribute("lapanganList", lapanganList);

        return "harga";
    }
}