package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.entity.Member;
import com.example.demo.entity.Lapangan;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.LapanganRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initMemberData(MemberRepository memberRepository,
                                             LapanganRepository lapanganRepository) {
        return args -> {

            if (memberRepository.count() == 0) {

                Member member1 = new Member();
                member1.setName("Budi Santoso");
                member1.setEmail("budi@gmail.com");
                member1.setPassword("budi123");
                member1.setPhone("081234567890");
                member1.setJenisKelamin("Laki-laki");
                member1.setTanggalLahir(LocalDate.of(2000, 5, 14));
                member1.setStatus("Aktif");
                member1.setCreatedLocalDateTime(LocalDateTime.now());
                member1.setUpdateLocalDateTime(LocalDateTime.now());
                memberRepository.save(member1);

                Member member2 = new Member();
                member2.setName("Siti Aminah");
                member2.setEmail("siti@gmail.com");
                member2.setPassword("siti123");
                member2.setPhone("081298765432");
                member2.setJenisKelamin("Perempuan");
                member2.setTanggalLahir(LocalDate.of(2002, 8, 20));
                member2.setStatus("Aktif");
                member2.setCreatedLocalDateTime(LocalDateTime.now());
                member2.setUpdateLocalDateTime(LocalDateTime.now());
                memberRepository.save(member2);

                Member member3 = new Member();
                member3.setName("Andi Wijaya");
                member3.setEmail("andi@gmail.com");
                member3.setPassword("andi123");
                member3.setPhone("081345678901");
                member3.setJenisKelamin("Laki-laki");
                member3.setTanggalLahir(LocalDate.of(1999, 3, 10));
                member3.setStatus("Aktif");
                member3.setCreatedLocalDateTime(LocalDateTime.now());
                member3.setUpdateLocalDateTime(LocalDateTime.now());
                memberRepository.save(member3);

                System.out.println("Data dummy Member berhasil ditambahkan!");
            }
        };
    }
}