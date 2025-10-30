package com.example.demo.services;

import com.example.demo.entities.Patient;
import java.util.List;

public interface PatientService {
    Patient save(Patient patient);
    List<Patient> findAll();
    Patient findById(int id);
    void deleteById(int id);

    // new
    List<Patient> findByAssignedDoctorId(Integer doctorId);
}
