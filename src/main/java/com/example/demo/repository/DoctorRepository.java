package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Doctor;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    List<Doctor> findBySpecializationIgnoreCase(String specialization);
    Optional<Doctor> findByEmailAndPassword(String email, String password); // simple login check
}
