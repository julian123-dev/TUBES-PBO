package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // Cari lewat email
    Optional<Member> findByEmail(String email);

    // Cek apa email dah dipake blm
    boolean existsByEmail(String email);
}