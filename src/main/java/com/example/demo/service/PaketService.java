package com.example.demo.service;

import com.example.demo.entity.Paket;
import com.example.demo.repository.PaketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaketService {

    @Autowired
    private PaketRepository paketRepository;

    // Ambil semua paket (aktif maupun tidak)
    public List<Paket> getAllPaket() {
        return paketRepository.findAll();
    }

    // Ambil paket yang aktif saja (untuk ditampilkan sebagai rekomendasi)
    public List<Paket> getPaketAktif() {
        return paketRepository.findByStatusTrue();
    }

    // Ambil paket berdasarkan ID
    public Optional<Paket> getPaketById(int idPaket) {
        return paketRepository.findById(idPaket);
    }
}