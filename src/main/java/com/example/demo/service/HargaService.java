package com.example.demo.service;

import com.example.demo.entity.Harga;
import com.example.demo.entity.Paket;
import com.example.demo.repository.HargaRepository;
import com.example.demo.repository.PaketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HargaService {

    @Autowired
    private HargaRepository hargaRepository;

    @Autowired
    private PaketRepository paketRepository;

    // ===== LAPANGAN =====

    // Ambil data lapangan berdasarkan ID
    public Optional<Harga> getLapanganById(int idLapangan) {
        return hargaRepository.findById(idLapangan);
    }

    // Ambil semua lapangan
    public List<Harga> getAllLapangan() {
        return hargaRepository.findAll();
    }

    // ===== PAKET =====

    // Ambil semua paket yang aktif
    public List<Paket> getPaketAktif() {
        return paketRepository.findByStatusTrue();
    }

    // ===== KALKULASI HARGA =====

    // Hitung total harga berdasarkan durasi jam dan harga per jam
    public double hitungTotal(double hargaPerJam, int durasiJam) {
        if (durasiJam <= 0) return 0;
        return hargaPerJam * durasiJam;
    }
}