package com.example.tablas.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentDto {

	private Long id;

	@NotBlank(message = "Este campo no debe estar vacío")
	@Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
	private String name;

	@NotBlank(message = "Este campo no debe estar vacío")
	@Size(min = 3, max = 20, message = "El apellido debe tener entre 3 y 20 caracteres")
	private String surname;

	public StudentDto() {
	}

	public StudentDto(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

}
