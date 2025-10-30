package com.example.demo.controller;

import com.example.demo.entities.Doctor;
import com.example.demo.entities.Patient;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.services.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientService patientService;

    @GetMapping("/login")
    public String loginForm() {
        return "doctor_login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        Optional<Doctor> found =
                doctorRepo.findByEmailAndPassword(email, password); // plaintext check
        if (found.isEmpty()) {
            model.addAttribute("error", "Invalid email or password");
            return "doctor_login";
        }
        session.setAttribute("doctorId", found.get().getId());
        session.setAttribute("doctorName", found.get().getName());
        return "redirect:/doctor/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Integer docId = (Integer) session.getAttribute("doctorId");
        if (docId == null) {
            return "redirect:/doctor/login";
        }
        List<Patient> patients = patientService.findByAssignedDoctorId(docId);
        model.addAttribute("patients", patients);
        model.addAttribute("doctor", doctorRepo.findById(docId).orElse(null));
        return "doctor_dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
