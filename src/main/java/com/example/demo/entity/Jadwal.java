package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "jadwal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jadwal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idJadwal;

    @ManyToOne
    @JoinColumn(name = "id_lapangan", nullable = false)
    private Lapangan lapangan;

    @Column(nullable = false)
    private LocalDate tanggal;

    @Column(name = "jam_mulai", nullable = false)
    private String jamMulai;

    @Column(name = "jam_selesai", nullable = false)
    private String jamSelesai;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable = true;

    public boolean cekSlotKosong() {
        return Boolean.TRUE.equals(this.isAvailable);
    }
}