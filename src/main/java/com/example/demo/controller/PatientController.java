package com.example.demo.controller;

import com.example.demo.entities.Patient;
import com.example.demo.entities.Doctor;
import com.example.demo.services.PatientService;
import com.example.demo.services.RecommendationService;
import com.example.demo.repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@Controller
public class PatientController {

    @Autowired
    private PatientService service;

    @Autowired
    private RecommendationService recommender;

    @Autowired
    private DoctorRepository doctorRepo;

    @GetMapping("/")
    public String welcome() {
        return "index";
    }

    @GetMapping("/patients")
    public String viewPatientList(Model model) {
        model.addAttribute("patients", service.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient_form";
    }

    @PostMapping("/save")
    public String savePatient(@ModelAttribute Patient patient, Model model) {
        var maybe = recommender.recommendDoctor(patient.getVisitReason());
        if (maybe.isPresent()) {
            Doctor doc = maybe.get();
            patient.setAssignedDoctorId(doc.getId());
            patient.setAssignedDoctorName(doc.getName());
        } else {
            patient.setAssignedDoctorId(null);
            patient.setAssignedDoctorName(null);
        }

        Patient savedPatient = service.save(patient);

        String formattedDateTime = "";
        if (patient.getAppointmentDateTime() != null) {
            formattedDateTime = patient.getAppointmentDateTime()
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        }

        model.addAttribute("patient", savedPatient);
        model.addAttribute("formattedDateTime", formattedDateTime);
        return "appointment_confirmation";
    }

    @GetMapping("/edit/{id}")
    public String editPatientForm(@PathVariable int id, Model model) {
        model.addAttribute("patient", service.findById(id));
        return "patient_form";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable int id) {
        service.deleteById(id);
        return "redirect:/patients";
    }
}
