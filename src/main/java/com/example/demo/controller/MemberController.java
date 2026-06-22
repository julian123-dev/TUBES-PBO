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

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/")
    public String home() {
        return "landing";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute Member member, Model model) {

        if (!memberService.validasiData(member)) {
            model.addAttribute("error", "Data belum lengkap, mohon isi semua field wajib");
            return "register";
        }

        if (memberService.isEmailExists(member.getEmail())) {
            model.addAttribute("error", "Email sudah terdaftar, silakan gunakan email lain");
            return "register";
        }

        memberService.registerMember(member);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                                 @RequestParam String password,
                                 Model model,
                                 HttpSession session) {

        Member member = memberService.login(email, password);

        if (member == null) {
            model.addAttribute("error", "Email atau password salah");
            return "login";
        }

        // Simpan data member yang login ke session
        session.setAttribute("member", member);

        model.addAttribute("member", member);
        return "daftarlapangan";
    }

    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable Long id, Model model) {
        Member member = memberService.getMemberById(id);
        model.addAttribute("member", member);
        return "profile";
    }

    @GetMapping("/profile/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Member member = memberService.getMemberById(id);
        model.addAttribute("member", member);
        return "edit-profile";
    }

    @PostMapping("/profile/{id}/edit")
    public String processEditProfile(@PathVariable Long id, @ModelAttribute Member member, HttpSession session) {
        Member updated = memberService.updateMember(id, member);

        // Update juga data di session, biar data terbaru ikut tersimpan
        session.setAttribute("member", updated);

        return "redirect:/profile/" + id;
    }

    // Logout: hapus data session
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}