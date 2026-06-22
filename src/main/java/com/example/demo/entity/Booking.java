package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relasi ke Member: banyak Booking bisa dimiliki oleh 1 Member
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // Relasi ke Lapangan: banyak Booking bisa terjadi di 1 Lapangan
    @ManyToOne
    @JoinColumn(name = "lapangan_id")
    private Lapangan lapangan;

    @Column(name = "tanggal_booking", nullable = false)
    private LocalDate tanggalBooking;

    @Column(name = "jam_mulai", nullable = false)
    private LocalTime jamMulai;

    @Column(name = "jam_selesai", nullable = false)
    private LocalTime jamSelesai;

    @Column(name = "status", length = 20)
    private String status;

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Lapangan getLapangan() {
        return lapangan;
    }

    public void setLapangan(Lapangan lapangan) {
        this.lapangan = lapangan;
    }

    public LocalDate getTanggalBooking() {
        return tanggalBooking;
    }

    public void setTanggalBooking(LocalDate tanggalBooking) {
        this.tanggalBooking = tanggalBooking;
    }

    public LocalTime getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(LocalTime jamMulai) {
        this.jamMulai = jamMulai;
    }

    public LocalTime getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(LocalTime jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}