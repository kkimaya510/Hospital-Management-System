package com.example.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Patient;
import com.example.demo.repository.PatientRepository;

@Service
public class PatientServiceImplement implements PatientService {
    @Autowired
    private PatientRepository repo;

    @Override
    public Patient save(Patient patient) {
        return repo.save(patient);
    }

    @Override
    public List<Patient> findAll() {
        return repo.findAll();
    }

    @Override
    public Patient findById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<Patient> findByAssignedDoctorId(Integer doctorId) {
        return repo.findByAssignedDoctorId(doctorId);
    }
}
