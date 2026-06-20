package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // Cari member berdasarkan email, dipakai untuk proses login
    Optional<Member> findByEmail(String email);

    // Cek apakah email sudah pernah dipakai, dipakai saat validasi registrasi
    boolean existsByEmail(String email);
}