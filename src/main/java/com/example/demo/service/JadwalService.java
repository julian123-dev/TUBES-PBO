package com.example.demo.service;

import com.example.demo.entity.Jadwal;
import com.example.demo.entity.Lapangan;
import com.example.demo.repository.JadwalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JadwalService {

    @Autowired
    private JadwalRepository jadwalRepository;

    public List<Jadwal> getJadwalByLapanganDanTanggal(Lapangan lapangan, LocalDate tanggal) {
        List<Jadwal> semua = jadwalRepository.findByLapanganAndTanggal(lapangan, tanggal);
        semua.sort((a, b) -> {
            if (!a.getIsAvailable() && b.getIsAvailable()) return -1;
            if (a.getIsAvailable() && !b.getIsAvailable()) return 1;
            return a.getJamMulai().compareTo(b.getJamMulai());
        });
        return semua;
    }

    public Optional<Jadwal> getById(Integer id) {
        return jadwalRepository.findById(id);
    }

    public void simpan(Jadwal jadwal) {
        jadwalRepository.save(jadwal);
    }

    public void generateSlotHarian(Lapangan lapangan, LocalDate tanggal) {
        List<Jadwal> existing = jadwalRepository.findByLapanganAndTanggal(lapangan, tanggal);
        if (!existing.isEmpty()) return;

        String jamBuka = lapangan.getJamBuka();
        String jamTutup = lapangan.getJamTutup();

        int jamMulai = Integer.parseInt(jamBuka.split(":")[0]);
        int jamAkhir = Integer.parseInt(jamTutup.split(":")[0]);

        for (int jam = jamMulai; jam < jamAkhir; jam++) {
            Jadwal slot = new Jadwal();
            slot.setLapangan(lapangan);
            slot.setTanggal(tanggal);
            slot.setJamMulai(String.format("%02d:00", jam));
            slot.setJamSelesai(String.format("%02d:00", jam + 1));
            slot.setIsAvailable(true);
            jadwalRepository.save(slot);
        }
    }
}