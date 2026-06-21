package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // Daftarkan member baru
    public Member registerMember(Member member) {
        member.setStatus("Aktif");
        member.setCreatedLocalDateTime(LocalDateTime.now());
        member.setUpdateLocalDateTime(LocalDateTime.now());
        return memberRepository.save(member);
    }

    // Cek login, return member kalau email dan password cocok
    public Member login(String email, String password) {
        Optional<Member> memberOptional = memberRepository.findByEmail(email);

        if (memberOptional.isEmpty()) {
            return null;
        }

        Member member = memberOptional.get();
        if (!member.getPassword().equals(password)) {
            return null;
        }

        return member;
    }

    // Cek apakah email sudah terdaftar
    public boolean isEmailExists(String email) {
        return memberRepository.existsByEmail(email);
    }

    // Ambil satu member berdasarkan id
    public Member getMemberById(Long id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        return memberOptional.orElse(null);
    }

    // Update data profil member
    public Member updateMember(Long id, Member updatedData) {
        Member member = getMemberById(id);

        if (member == null) {
            return null;
        }

        member.setName(updatedData.getName());
        member.setPhone(updatedData.getPhone());
        member.setEmail(updatedData.getEmail());
        member.setJenisKelamin(updatedData.getJenisKelamin());
        member.setTanggalLahir(updatedData.getTanggalLahir());
        member.setUpdateLocalDateTime(LocalDateTime.now());

        return memberRepository.save(member);
    }

    // Menampilkan status member dalam bentuk teks
    public String lihatStatus(Long id) {
        Member member = getMemberById(id);
        if (member == null) {
            return "Member tidak ditemukan";
        }
        return member.getStatus();
    }

    // Validasi kelengkapan data member sebelum disimpan
    public boolean validasiData(Member member) {
        if (member.getName() == null || member.getName().isEmpty()) {
            return false;
        }
        if (member.getEmail() == null || member.getEmail().isEmpty()) {
            return false;
        }
        if (member.getPassword() == null || member.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }
}