package com.example.demo.service;

import com.example.demo.entity.Harga;
import com.example.demo.repository.HargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HargaService {

    @Autowired
    private HargaRepository hargaRepository;

    public Optional<Harga> getLapanganById(int idLapangan) {
        return hargaRepository.findById(idLapangan);
    }

    public List<Harga> getAllLapangan() {
        return hargaRepository.findAll();
    }

    public double hitungTotal(double hargaPerJam, int durasiJam) {
        if (durasiJam <= 0) return 0;
        return hargaPerJam * durasiJam;
    }
}