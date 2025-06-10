package com.example.tablas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tablas.dtos.StudentDto;
import com.example.tablas.entity.Student;
import com.example.tablas.services.StudentService;

@SpringBootTest
class TablasControllerTest {

	@Autowired
	private StudentService studentService;
	
	@Test
	public void newStudent() {

		StudentDto waited = new StudentDto("Alicia", "Sánchez"); 		
		Student student = new Student("Alicia", "Sánchez");
		final Student result = studentService.save(student);
		
		Assertions.assertEquals(waited.getName(), result.getName());
	}
	
}
