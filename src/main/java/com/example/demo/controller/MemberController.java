package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    // Redirect halaman utama langsung ke halaman register
    @GetMapping("/")
    public String home() {
        return "redirect:/register";
    }

    // Menampilkan halaman form registrasi
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }

    // Memproses data dari form registrasi
    @PostMapping("/register")
    public String processRegister(@ModelAttribute Member member, Model model) {

        if (memberService.isEmailExists(member.getEmail())) {
            model.addAttribute("error", "Email sudah terdaftar, silakan gunakan email lain");
            return "register";
        }

        memberService.registerMember(member);
        return "redirect:/login";
    }

    // Menampilkan halaman form login
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Memproses login
    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                                 @RequestParam String password,
                                 Model model) {

        Member member = memberService.login(email, password);

        if (member == null) {
            model.addAttribute("error", "Email atau password salah");
            return "login";
        }

        model.addAttribute("member", member);
        return "profile";
    }

    // Menampilkan profile berdasarkan id
    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable Long id, Model model) {
        Member member = memberService.getMemberById(id);
        model.addAttribute("member", member);
        return "profile";
    }

    // Menampilkan halaman form edit profile
    @GetMapping("/profile/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Member member = memberService.getMemberById(id);
        model.addAttribute("member", member);
        return "edit-profile";
    }

    // Memproses perubahan data profile
    @PostMapping("/profile/{id}/edit")
    public String processEditProfile(@PathVariable Long id, @ModelAttribute Member member) {
        memberService.updateMember(id, member);
        return "redirect:/profile/" + id;
    }
}