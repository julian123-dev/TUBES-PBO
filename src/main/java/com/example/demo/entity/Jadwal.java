package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "jadwal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Jadwal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idJadwal;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
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