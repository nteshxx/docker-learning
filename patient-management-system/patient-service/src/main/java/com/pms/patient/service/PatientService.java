package com.pms.patient.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pms.patient.dto.PatientRequestDto;
import com.pms.patient.dto.PatientResponseDto;
import com.pms.patient.exception.EmailAlreadyExistsException;
import com.pms.patient.exception.PatientNotFoundException;
import com.pms.patient.mapper.PatientMapper;
import com.pms.patient.model.Patient;
import com.pms.patient.repository.PatientRepository;

@Service
public class PatientService {

	private final PatientRepository patientRepository;

	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	public List<PatientResponseDto> getAllPatients() {
		List<Patient> patients = patientRepository.findAll();
		return patients.stream().map(PatientMapper::toDto).toList();
	}
	
	public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {
		if (patientRepository.existsByEmail(patientRequestDto.getEmail())) {
			throw new EmailAlreadyExistsException("A patient with this email already exists: " + patientRequestDto.getEmail());
		}
		Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDto));
		return PatientMapper.toDto(newPatient);
	}
	
	public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto) {
		Patient patient = patientRepository.findById(id).orElseThrow(
				() -> new PatientNotFoundException("Patient not found with ID: " + id.toString()));
		if (patientRepository.existsByEmailAndIdNot(patientRequestDto.getEmail(), id)) {
			throw new EmailAlreadyExistsException("A patient with this email already exists: " + patientRequestDto.getEmail());
		}
		patient.setName(patientRequestDto.getName());
		patient.setAddress(patientRequestDto.getAddress());
		patient.setEmail(patientRequestDto.getEmail());
		patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
		Patient updatedPatient = patientRepository.save(patient);
		return PatientMapper.toDto(updatedPatient);
	}
	
	public void deletePatient(UUID id) {
		patientRepository.deleteById(id);
	}
}
