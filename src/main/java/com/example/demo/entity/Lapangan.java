package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "lapangan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lapangan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLapangan;

    @Column(nullable = false)
    private String nama;

    @Column(nullable = false)
    private String jenis;

    @Column(nullable = false)
    private String lokasi;

    @Column(nullable = false)
    private String status;

    @Column(name = "harga_per_jam", nullable = false)
    private Float hargaPerJam;

    @Column(name = "jam_buka", nullable = false)
    private String jamBuka;

    @Column(name = "jam_tutup", nullable = false)
    private String jamTutup;

    @Column(nullable = true)
    private String foto;

    @ToString.Exclude
    @OneToMany(mappedBy = "lapangan", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Jadwal> daftarJadwal;

    public boolean getKetersediaan() {
        return "Aktif".equalsIgnoreCase(this.status);
    }
}