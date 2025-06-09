package com.example.tablas.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentDto {

		
	private Long id;
	
	@NotEmpty (message = "Este campo no debe estar vacío")
	@Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
	private String name;
	
	@NotEmpty (message = "Este campo no debe estar vacío")
	@Size(min = 3, max = 20, message = "El apellido debe tener entre 3 y 20 caracteres")
	private String surname;
	
}
