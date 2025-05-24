package com.pms.patient.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patient {

	@Id
	@GeneratedValue()
	private UUID id;
	
	@NotNull
	private String name;
	
	@NotNull
	@Email
	@Column(unique = true)
	private String email;
	
	@NotNull
	private String address;
	
	@NotNull
	private LocalDate dateOfBirth;
	
	@NotNull
	private LocalDate registeredDate;
	
}
