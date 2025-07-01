package com.example.tablas.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class StudentDtoTest {

	public StudentDto studentDto;
	public ValidatorFactory factory;
	public Validator validator;
	

	private void assertErrorContainsName(Set<ConstraintViolation<StudentDto>> errors) {

		assertErrorContains(errors, "name", "El nombre debe tener entre 3 y 20 caracteres");

	}

	private void assertErrorContainsSurname(Set<ConstraintViolation<StudentDto>> errors) {

		assertErrorContains(errors, "surname", "El apellido debe tener entre 3 y 20 caracteres");
	}

	private void assertErrorNullName(Set<ConstraintViolation<StudentDto>> errors) {

		assertErrorContains(errors, "name", "Este campo no debe estar vacío");

	}
	
	private void assertErrorNullSurname(Set<ConstraintViolation<StudentDto>> errors) {

		assertErrorContains(errors, "surname", "Este campo no debe estar vacío");

	}
	
	private void assertErrorContains(Set<ConstraintViolation<StudentDto>> errors, String attribute, String message) {

		assertTrue(errors.stream().anyMatch(
				error -> error.getPropertyPath().toString().equals(attribute) && error.getMessage().equals(message)));
	}

	@BeforeEach
	public void setUp() {
		
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

	}

	@Test
	public void newStudent_Valid() {

		studentDto = new StudentDto("Ana", "García");

		Set<ConstraintViolation<StudentDto>> errors = validator.validate((studentDto));
		assertTrue(errors.isEmpty());
		assertEquals(studentDto.getName(), "Ana");
		assertEquals(studentDto.getSurname(), "García");
	}

	@Test
	public void newStudent_Empty() {

		studentDto = new StudentDto("", "");

		Set<ConstraintViolation<StudentDto>> errors = validator.validate(studentDto);
		assertFalse(errors.isEmpty());
		assertEquals(4, errors.size()); // Incumple 4 restricciones: 2 vacíos, y, a la vez, 2 nulos
		assertErrorContainsName(errors);
		assertErrorContainsSurname(errors);
	}

	@Test
	public void newStudent_Space() {

		studentDto = new StudentDto(" ", " ");

		Set<ConstraintViolation<StudentDto>> errors = validator.validate((studentDto));
		assertFalse(errors.isEmpty());
		assertEquals(2, errors.size());
		assertErrorContainsName(errors);
		assertErrorContainsSurname(errors);
	}

	@Test
	public void newStudent_Null() {

		studentDto = new StudentDto(null, null);

		Set<ConstraintViolation<StudentDto>> errors = validator.validate((studentDto));
		assertFalse(errors.isEmpty());
		assertEquals(2, errors.size());
		assertErrorNullName(errors);
		assertErrorNullSurname(errors);

	}

	@Test
	public void newStudent_Short() {

		studentDto = new StudentDto("Al", "Ve");

		Set<ConstraintViolation<StudentDto>> errors = validator.validate((studentDto));
		assertFalse(errors.isEmpty());
		assertEquals(2, errors.size());
		assertErrorContainsName(errors);
		assertErrorContainsSurname(errors);

	}

	@Test
	public void newStudent_Long() {

		studentDto = new StudentDto("María de los Ángeles Agustina", "Martínez de León Gracia");

		Set<ConstraintViolation<StudentDto>> errors = validator.validate((studentDto));
		assertFalse(errors.isEmpty());
		assertEquals(2, errors.size());
		assertErrorContainsName(errors);
		assertErrorContainsSurname(errors);
	}

	@Test
	public void newStudent_RareCharacters() {

		studentDto = new StudentDto("{}çñäéíóúÜÇÑÁÉÍÓÚÜ()[]", "{}çñäéíóúÜÇÑÁÉÍÓÚÜ()[]");

		Set<ConstraintViolation<StudentDto>> errors = validator.validate((studentDto));
		assertFalse(errors.isEmpty());
		assertEquals(2, errors.size());
		assertErrorContainsName(errors);
		assertErrorContainsSurname(errors);
	}

	@Test
	public void newStudent_LimitsShort() {

		studentDto = new StudentDto("Ana", "Gol");
		Set<ConstraintViolation<StudentDto>> errors = validator.validate((studentDto));
		assertTrue(errors.isEmpty());

	}

	@Test
	public void newStudent_LimitsLong() {

		studentDto = new StudentDto("María de los Ángeles", "de Aragón y Urquijos");
		Set<ConstraintViolation<StudentDto>> errors = validator.validate((studentDto));
		assertTrue(errors.isEmpty());

	}

}
