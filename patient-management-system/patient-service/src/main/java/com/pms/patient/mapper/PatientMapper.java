package com.pms.patient.mapper;

import java.time.LocalDate;

import com.pms.patient.dto.PatientRequestDto;
import com.pms.patient.dto.PatientResponseDto;
import com.pms.patient.model.Patient;

public class PatientMapper {

	public static PatientResponseDto toDto(Patient patient) {
		PatientResponseDto patientDto = new PatientResponseDto();
		patientDto.setId(patient.getId().toString());
		patientDto.setName(patient.getName());
		patientDto.setEmail(patient.getEmail());
		patientDto.setAddress(patient.getAddress());
		patientDto.setDateOfBirth(patient.getDateOfBirth().toString());
		
		return patientDto;
	}
	
	public static Patient toModel(PatientRequestDto patientRequestDto) {
		Patient patient = new Patient();
		patient.setName(patientRequestDto.getName());
		patient.setEmail(patientRequestDto.getEmail());
		patient.setAddress(patientRequestDto.getAddress());
		patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
		patient.setRegisteredDate(LocalDate.parse(patientRequestDto.getRegisteredDate()));
		
		return patient;
	}
}
