package com.pms.patient.dto;

import com.pms.patient.dto.validators.CreatePatientValidationGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequestDto {
	
	@NotBlank
	@Size(max = 100, message = "Name cannot exceed 100 characters")
	private String name;
	
	@NotBlank
	@Email(message = "Email should be valid")
	private String email;
	
	@NotBlank(message = "Address is required")
	private String address;
	
	@NotBlank(message = "Date of birth is required")
	private String dateOfBirth;
	
	@NotBlank(groups = CreatePatientValidationGroup.class, message = "Registered date is required")
	private String registeredDate;

}
