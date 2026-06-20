package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.History;
import com.example.demo.entity.Pembayaran;
import com.example.demo.repository.PembayaranRepository;

@Service
public class PembayaranService {

    @Autowired
    private PembayaranRepository pembayaranRepository;

    @Autowired
    private HistoryService historyService;

    public List<Pembayaran> getAllPembayaran() {
        return pembayaranRepository.findAll();
    }

    public void simpanPembayaran(Pembayaran pembayaran) {

        pembayaranRepository.save(pembayaran);

        History history = new History();

        history.setNamaMember(
                pembayaran.getNamaPelanggan());

        history.setMetodePembayaran(
                pembayaran.getMetodePembayaran());

        history.setJumlah(
                pembayaran.getJumlah());

        historyService.simpanHistory(history);
    }

    public void hapusPembayaran(Long id) {
        pembayaranRepository.deleteById(id);
    }
}