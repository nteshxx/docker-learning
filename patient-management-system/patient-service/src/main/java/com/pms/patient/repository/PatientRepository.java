package com.pms.patient.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pms.patient.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
	
	public boolean existsByEmail(String email);
	public boolean existsByEmailAndIdNot(String email, UUID id);
}
