package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paket")
public class Paket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPaket;

    @Column(name = "nama_paket", nullable = false)
    private String namaPaket;

    @Column(name = "durasi_hari", nullable = false)
    private int durasiHari;

    @Column(name = "harga", nullable = false)
    private double harga;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "status", nullable = false)
    private boolean status;
}