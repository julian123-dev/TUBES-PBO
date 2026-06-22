package com.example.demo.service;

import com.example.demo.entity.Lapangan;
import com.example.demo.repository.LapanganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LapanganService {

    @Autowired
    private LapanganRepository lapanganRepository;

    public Lapangan tambahLapangan(Lapangan lapangan) {
        return lapanganRepository.save(lapangan);
    }

    public List<Lapangan> lihatDaftarLapangan() {
        return lapanganRepository.findAll();
    }

    public Optional<Lapangan> lihatDetailLapangan(Integer idLapangan) {
        return lapanganRepository.findById(idLapangan);
    }

    public Lapangan updateLapangan(Integer idLapangan, Lapangan dataBaru) {
        Lapangan lapangan = lapanganRepository.findById(idLapangan)
                .orElseThrow(() -> new RuntimeException("Lapangan dengan id " + idLapangan + " tidak ditemukan"));

        lapangan.setNama(dataBaru.getNama());
        lapangan.setJenis(dataBaru.getJenis());
        lapangan.setLokasi(dataBaru.getLokasi());
        lapangan.setStatus(dataBaru.getStatus());
        lapangan.setHargaPerJam(dataBaru.getHargaPerJam());
        lapangan.setJamBuka(dataBaru.getJamBuka());
        lapangan.setJamTutup(dataBaru.getJamTutup());

        return lapanganRepository.save(lapangan);
    }

    public void hapusLapangan(Integer idLapangan) {
        if (!lapanganRepository.existsById(idLapangan)) {
            throw new RuntimeException("Lapangan dengan id " + idLapangan + " tidak ditemukan");
        }
        lapanganRepository.deleteById(idLapangan);
    }

    public List<Lapangan> cariByJenis(String jenis) {
        return lapanganRepository.findByJenisIgnoreCase(jenis);
    }

    public List<Lapangan> cariByNama(String keyword) {
        return lapanganRepository.findByNamaContainingIgnoreCase(keyword);
    }
}