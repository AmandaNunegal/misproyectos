package com.example.tablas.dataprovider;

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

	public static Student studentComplete () {

		Student student = new Student("Ana", "Sánchez");
		student.setId(10L);
		student.setCountry("España");
		return student;
	}
	
	public static Student student2 () {

		Student student = new Student("Ana", "Sánchez");
		student.setId(10L);
		return student;
	}

	public static Student studentNull () {

		Student student = new Student(null, null);		
		return student;
	}
	
	public static Student student1IdNull () {

		Student student = new Student("Ana", "Sánchez");		
		return student;
	}
	
	public static Student studentEmptyName () {

		Student student = new Student("", "Sánchez");		
		return student;
	}
	
	public static Student studentEmptyLastname () {

		Student student = new Student("Ana", "");		
		return student;
	}
		
	
	public static Student studentSpacesName () {

		Student student = new Student(" ", "Sánchez");		
		return student;
	}
	
	public static Student studentSpacesLastname () {

		Student student = new Student("Ana", " ");		
		return student;
	}
	
	
	public static Student studentNullName () {

		Student student = new Student(null, "Sánchez");		
		return student;
	}
	
	public static Student studentNullLastname () {

		Student student = new Student("Ana", null);		
		return student;
	}


	public static Student studentShortName () {

		Student student = new Student("Al", "Sánchez");		
		return student;
	}
	
	public static Student studentShortLastname () {

		Student student = new Student("Álvaro", "Ve");		
		return student;
	}
		
	public static Student studentLongName () {

		Student student = new Student("María de los Ángeles Agustina", "Martínez");		
		return student;
	}
	
	public static Student studentLongLastname () {

		Student student = new Student("Alicia", "Martínez de Aragón Gracia");		
		return student;
	}
	
	public static Student studentSpecialCharName() {

		Student student = new Student("{}çñäéíóúÜÇÑÁÉÍÓÚÜ()[]", "Martínez");		
		return student;
	}
	
	public static Student studentSpecialCharLastname() {

		Student student = new Student("Alicia", "{}çñäéíóúÜÇÑÁÉÍÓÚÜ()[]");		
		return student;
	}
	
	public static Student studentLimitMinName() {

		Student student = new Student("Ana", "García");		
		return student;
	}
	
	public static Student studentLimitMinLastname() {

		Student student = new Student("Alicia", "Uno");		
		return student;
	}
	
	public static Student studentLimitMaxName() {

		Student student = new Student("María de los Ángeles", "García");		
		return student;
	}
	
	public static Student studentLimitMaxLastname() {

		Student student = new Student("Luis", "de Aragón y Urquijos");		
		return student;
	}
	
	
	
	
	public static List<Student> listStudent () {

		List<Student> listStudent  = new ArrayList<>();
		
		Student student1 = new Student("Alicia", "Sánchez");
		student1.setId(10L);
		
		listStudent .add(student1);
		
		Student student2 = new Student("Emilia", "Martín");
		student2.setId(20L);
		
		listStudent .add(student2);
		
		Student student3 = new Student("Jaime", "Urrutia");
		student3.setId(30L);
		
		listStudent .add(student3);		

		return listStudent ;
	}

	public static Optional<Student> studentOptional () {

		Student student = new Student("Ana", "Sánchez");
		student.setId(10L);
		student.setCountry("España");

		return Optional.of(student);
	}	
	
	public static StudentDto studentDto1 () {

		StudentDto studentDto = new StudentDto("Ana", "Sánchez");
		studentDto.setId(10L);		

		return studentDto;
	}	
	
	
	public static StudentDto studentDto2 () {

		StudentDto student = new StudentDto("Ana", "Sánchez");
		student.setId(10L);
		return student;
	}
	
	
	public static StudentDto studentDto1IdNull () {

		StudentDto studentDto = new StudentDto("Ana", "Sánchez");			

		return studentDto;
	}	
	
	public static Student studentNullDto () {

		Student student = new Student(null, null);		
		return student;
	}
	
	public static Student studentEmptyDto () {

		Student student = new Student("", "");		
		return student;
	}
	
	
	
	public static List<StudentDto> listStudentDto () {

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