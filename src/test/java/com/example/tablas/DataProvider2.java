package com.example.tablas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.tablas.dtos.StudentDto;
import com.example.tablas.entity.Student;

public class DataProvider {

	Student student;
	Student student2;
	Student student3;

	Optional<Student> optStudent;

	public static Student student1Mock() {

		Student student = new Student("Ana", "Sánchez");
		student.setId(10L);
		student.setCountry("España");
		return student;
	}
	
	public static Student student2Mock() {

		Student student = new Student("Ana", "Sánchez");
		student.setId(10L);
		return student;
	}

	public static Student studentNullMock() {

		Student student = new Student(null, null);		
		return student;
	}
	
	public static Student student1IdNullMock() {

		Student student = new Student("Ana", "Sánchez");		
		return student;
	}
	
	public static Student studentEmptyMock() {

		Student student = new Student("", "");		
		return student;
	}

	public static List<Student> listStudentMock() {

		List<Student> listStudentMock = new ArrayList<>();
		
		Student student1 = new Student("Alicia", "Sánchez");
		student1.setId(10L);
		
		listStudentMock.add(student1);
		
		Student student2 = new Student("Emilia", "Martín");
		student2.setId(20L);
		
		listStudentMock.add(student2);
		
		Student student3 = new Student("Jaime", "Urrutia");
		student3.setId(30L);
		
		listStudentMock.add(student3);		

		return listStudentMock;
	}

	public static Optional<Student> studentOptionalMock() {

		Student student = new Student("Ana", "Sánchez");
		student.setId(10L);
		student.setCountry("España");

		return Optional.of(student);
	}	
	
	public static StudentDto studentDto1Mock() {

		StudentDto studentDto = new StudentDto("Ana", "Sánchez");
		studentDto.setId(10L);		

		return studentDto;
	}	
	
	
	public static StudentDto studentDto2Mock() {

		StudentDto student = new StudentDto("Ana", "Sánchez");
		student.setId(10L);
		return student;
	}
	
	
	public static StudentDto studentDto1IdNullMock() {

		StudentDto studentDto = new StudentDto("Ana", "Sánchez");			

		return studentDto;
	}	
	
	public static Student studentNullDtoMock() {

		Student student = new Student(null, null);		
		return student;
	}
	
	public static Student studentEmptyDtoMock() {

		Student student = new Student("", "");		
		return student;
	}
	
	
	
	public static List<StudentDto> listStudentDtoMock() {

		List<StudentDto> listStudentDto = new ArrayList<>();
		
		StudentDto studentDto1 = new StudentDto("Alicia", "Sánchez");
		studentDto1.setId(10L);
		
		listStudentDto.add(studentDto1);
		
		StudentDto studentDto2 = new StudentDto("Emilia", "Martín");
		studentDto2.setId(20L);
		
		listStudentDto.add(studentDto2);
		
		StudentDto studentDto3 = new StudentDto("Jaime", "Urrutia");
		studentDto3.setId(30L);
		
		listStudentDto.add(studentDto3);		

		return listStudentDto;
	}
	
}