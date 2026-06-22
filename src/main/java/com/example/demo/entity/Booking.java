package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBooking;

    @ManyToOne
    @JoinColumn(name = "id_member", nullable = true)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "id_jadwal", nullable = false)
    private Jadwal jadwal;

    @Column(name = "tanggal_booking", nullable = false)
    private LocalDate tanggalBooking;

    @Column(nullable = false)
    private String status;

    @Column(name = "total_harga", nullable = true)
    private Float totalHarga;

    public void konfirmasi() {
        this.status = "Dikonfirmasi";
    }

    public void batalkan() {
        this.status = "Dibatalkan";
        if (this.jadwal != null) {
            this.jadwal.setIsAvailable(true);
        }
    }

    public Float hitungHarga() {
        if (this.jadwal != null && this.jadwal.getLapangan() != null) {
            this.totalHarga = this.jadwal.getLapangan().getHargaPerJam();
        }
        return this.totalHarga;
    }
}