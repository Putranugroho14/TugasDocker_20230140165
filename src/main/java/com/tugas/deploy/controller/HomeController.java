package com.tugas.deploy.controller;

import com.tugas.deploy.model.Mahasiswa;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    // Data temporary (tidak disimpan di database)
    private final List<Mahasiswa> mahasiswaList = new ArrayList<>();

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }
        model.addAttribute("mahasiswaList", mahasiswaList);
        return "home";
    }

    @GetMapping("/form")
    public String form(HttpSession session, Model model) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }
        model.addAttribute("mahasiswa", new Mahasiswa());
        return "form";
    }

    @PostMapping("/form")
    public String submitForm(@ModelAttribute Mahasiswa mahasiswa,
                             HttpSession session) {
        if (session.getAttribute("loggedIn") == null) {
            return "redirect:/login";
        }
        mahasiswaList.add(mahasiswa);
        return "redirect:/home";
    }
}
