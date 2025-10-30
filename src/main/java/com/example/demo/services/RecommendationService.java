package com.example.demo.services;

import org.springframework.stereotype.Service;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.entities.Doctor;

import java.util.*;

@Service
public class RecommendationService {

    private final DoctorRepository doctorRepo;

    public RecommendationService(DoctorRepository doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    // map keywords -> specialization
    private static final Map<String, String> DISEASE_MAP = Map.ofEntries(
        Map.entry("heart", "Cardiologist"),
        Map.entry("chest", "Cardiologist"),
        Map.entry("skin", "Dermatologist"),
        Map.entry("acne", "Dermatologist"),
        Map.entry("fever", "General Physician"),
        Map.entry("cough", "General Physician"),
        Map.entry("bone", "Orthopedic"),
        Map.entry("fracture", "Orthopedic"),
        Map.entry("breath", "Pulmonologist"),
        Map.entry("asthma", "Pulmonologist"),
        Map.entry("diabet", "Endocrinologist"),
        Map.entry("thyroid", "Endocrinologist"),
        Map.entry("preg", "Gynecologist"),
        Map.entry("pregnancy", "Gynecologist"),
        Map.entry("stress", "Psychiatrist"),
        Map.entry("depress", "Psychiatrist")
    );

    public Optional<Doctor> recommendDoctor(String visitReason) {
        if (visitReason == null || visitReason.isBlank()) {
            return doctorRepo.findAll().stream()
                    .max(Comparator.comparingInt(Doctor::getYearsOfExperience));
        }

        String s = visitReason.toLowerCase(Locale.ROOT);
        String specialization = null;
        for (var e : DISEASE_MAP.entrySet()) {
            if (s.contains(e.getKey())) {
                specialization = e.getValue();
                break;
            }
        }

        List<Doctor> pool = (specialization != null)
                ? doctorRepo.findBySpecializationIgnoreCase(specialization)
                : doctorRepo.findAll();

        return pool.stream().max(Comparator.comparingInt(Doctor::getYearsOfExperience));
    }
}
