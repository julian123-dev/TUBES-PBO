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

            if (lapanganRepository.count() == 0) {

                Lapangan futsal1 = new Lapangan();
                futsal1.setNama("Lapangan Futsal A");
                futsal1.setJenis("Futsal");
                futsal1.setLokasi("Gedung Utama Lt.1");
                futsal1.setStatus("Aktif");
                futsal1.setHargaPerJam(60000f);
                futsal1.setJamBuka("08:00");
                futsal1.setJamTutup("23:00");
                futsal1.setFoto("futsal1.jpg");
                lapanganRepository.save(futsal1);

                Lapangan futsal2 = new Lapangan();
                futsal2.setNama("Lapangan Futsal B");
                futsal2.setJenis("Futsal");
                futsal2.setLokasi("Gedung Utama Lt.1");
                futsal2.setStatus("Aktif");
                futsal2.setHargaPerJam(60000f);
                futsal2.setJamBuka("08:00");
                futsal2.setJamTutup("23:00");
                futsal2.setFoto("futsal2.png");
                lapanganRepository.save(futsal2);

                Lapangan basket1 = new Lapangan();
                basket1.setNama("Lapangan Basket A");
                basket1.setJenis("Basket");
                basket1.setLokasi("Gedung Utama Lt.2");
                basket1.setStatus("Aktif");
                basket1.setHargaPerJam(70000f);
                basket1.setJamBuka("06:00");
                basket1.setJamTutup("21:00");
                basket1.setFoto("basket1.jpg");
                lapanganRepository.save(basket1);

                Lapangan basket2 = new Lapangan();
                basket2.setNama("Lapangan Basket B");
                basket2.setJenis("Basket");
                basket2.setLokasi("Gedung Utama Lt.2");
                basket2.setStatus("Aktif");
                basket2.setHargaPerJam(70000f);
                basket2.setJamBuka("06:00");
                basket2.setJamTutup("21:00");
                basket2.setFoto("basket2.png");
                lapanganRepository.save(basket2);

                Lapangan badminton1 = new Lapangan();
                badminton1.setNama("Lapangan Badminton A");
                badminton1.setJenis("Badminton");
                badminton1.setLokasi("Gedung Utama Lt.3");
                badminton1.setStatus("Aktif");
                badminton1.setHargaPerJam(35000f);
                badminton1.setJamBuka("07:00");
                badminton1.setJamTutup("22:00");
                badminton1.setFoto("badminton1.jpg");
                lapanganRepository.save(badminton1);

                Lapangan badminton2 = new Lapangan();
                badminton2.setNama("Lapangan Badminton B");
                badminton2.setJenis("Badminton");
                badminton2.setLokasi("Gedung Utama Lt.3");
                badminton2.setStatus("Aktif");
                badminton2.setHargaPerJam(35000f);
                badminton2.setJamBuka("07:00");
                badminton2.setJamTutup("22:00");
                badminton2.setFoto("badminton2.jpg");
                lapanganRepository.save(badminton2);

                System.out.println("Data dummy Lapangan berhasil ditambahkan!");
            }
        };
    }
}