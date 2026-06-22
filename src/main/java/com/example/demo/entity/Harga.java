package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lapangan")
public class Harga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLapangan;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "jenis", nullable = false)
    private String jenis;

    @Column(name = "lokasi")
    private String lokasi;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "harga_per_jam", nullable = false)
    private double hargaPerJam;

    @Column(name = "jam_buka")
    private String jamBuka;

    @Column(name = "jam_tutup")
    private String jamTutup;
}