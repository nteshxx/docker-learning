package com.pms.patient.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.pms.patient.dto.PatientRequestDto;
import com.pms.patient.dto.PatientResponseDto;
import com.pms.patient.dto.validators.CreatePatientValidationGroup;
import com.pms.patient.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/patient")
@Tag(name = "Patient", description = "API for managing Patients")
public class PatientController {

	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@GetMapping
	@Operation(summary = "Get Patients")
	public ResponseEntity<List<PatientResponseDto>> getPatients() {
		List<PatientResponseDto> patients = patientService.getAllPatients();
		return ResponseEntity.ok().body(patients);
	}
	
	@PostMapping
	@Operation(summary = "Create a new Patient")
	public ResponseEntity<PatientResponseDto> addPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDto patientRequestDto) {
		PatientResponseDto patient = patientService.createPatient(patientRequestDto);
		return ResponseEntity.ok().body(patient);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Update a Patient")
	public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto) {
		PatientResponseDto patient = patientService.updatePatient(id, patientRequestDto);
		return ResponseEntity.ok().body(patient);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a Patient")
	public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
		patientService.deletePatient(id);
		return ResponseEntity.noContent().build();
	}
}
