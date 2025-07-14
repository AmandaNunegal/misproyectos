package com.example.tablas.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.tablas.dataprovider.DataProvider;
import com.example.tablas.dataprovider.DataProvider2;
import com.example.tablas.dataprovider.StudentDataTest;
import com.example.tablas.dtos.StudentDto;
import com.example.tablas.entity.Student;
import com.example.tablas.repository.StudentRepository;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

//when(studentService.findById(1L)).thenReturn(student);
//when(studentService.findById(500L)).thenReturn(null);

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest_BK {

	private static StudentDataTest testData;
	private static List<Student> listStudent;
	private static List<StudentDto> listStudentDto;
	private static Student studentComplete;
	private static StudentDto studentDto1;
	private static Student student1IdNull;
	private static StudentDto studentDto1IdNull;
	private static Student student2;
	private static StudentDto studentDto2;
	private static Optional<Student> studentOptional;
	private static Student studentEmptyName;
	private static Student studentEmptyLastname;
	private static Student studentNullName;
	private static Student studentNullLastname;
	private static Student studentSpacesName;
	private static Student studentSpacesLastname;
	private static Student studentNull;
	private static Student studentShortName;
	private static Student studentShortLastname;
	private static Student studentLongName;
	private static Student studentLongLastname;
	private static Student studentSpecialCharName;
	private static Student studentSpecialCharLastname;
	private static Student studentLimitMinName;
	private static Student studentLimitMinLastname;
	private static Student studentLimitMaxName;
	private static Student studentLimitMaxLastname;

	private static Stream<Student> studentOk() {
		return Stream.of(studentComplete, studentLimitMinName, studentLimitMinLastname, studentLimitMaxName,
				studentLimitMaxLastname);
	}

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private StudentService studentService;

	@BeforeEach
	public void setUp() throws IOException {

		ObjectMapper oMapper = new ObjectMapper();
		try (InputStream inputStream = StudentServiceTest_BK.class.getClassLoader()
				.getResourceAsStream("data/testData.json")) {
			testData = oMapper.readValue(inputStream, StudentDataTest.class);
		}
		listStudent = DataProvider.listStudent();
		// listStudentDto = DataProvider.listStudentDto();
		studentComplete = DataProvider.studentComplete();
		// studentDto1 = DataProvider.studentDto1();
		student1IdNull = DataProvider.student1IdNull();
		// studentDto1IdNull = DataProvider.studentDto1IdNull();
		student2 = DataProvider.student2();
		// studentDto2 = DataProvider.studentDto2();
		studentOptional = DataProvider.studentOptional();
		studentEmptyName = DataProvider.studentEmptyName();
		studentEmptyLastname = DataProvider.studentEmptyLastname();
		studentNullName = DataProvider.studentNullLastname();
		studentNullLastname = DataProvider.studentNullLastname();
		studentSpacesName = DataProvider.studentSpacesName();
		studentSpacesLastname = DataProvider.studentSpacesLastname();
		studentShortName = DataProvider.studentShortName();
		studentShortLastname = DataProvider.studentShortLastname();
		studentLongName = DataProvider.studentLongName();
		studentLongLastname = DataProvider.studentLongLastname();
		studentSpecialCharName = DataProvider.studentSpecialCharName();
		studentSpecialCharLastname = DataProvider.studentSpecialCharLastname();
		studentLimitMinName = DataProvider.studentLimitMinName();
		studentLimitMinLastname = DataProvider.studentLimitMinLastname();
		studentLimitMaxName = DataProvider.studentLimitMaxName();
		studentLimitMaxLastname = DataProvider.studentLimitMaxLastname();

	}

	@Test
	public void pruebas() {

		DataProvider2 dataProvider2 = new DataProvider2();
		dataProvider2.myMapper();

	}

	@ParameterizedTest
	@MethodSource("studentOk")
	public void studentSave_ok(Student studentOk) {

		when(studentRepository.save(studentOk)).thenReturn(studentOk);
		Student resultado = studentService.save(studentOk);
		assertEquals(studentOk, resultado);
		verify(studentRepository).save(studentOk);

	}

	@Nested
	class StudentSaveError {

		private static Stream<Student> studentNameErrors() {
			return Stream.of(studentEmptyName, studentSpacesName, studentShortName, studentLongName,
					studentSpecialCharName);
		}

		private static Stream<Student> studentLastnameErrors() {
			return Stream.of(studentEmptyLastname, studentSpacesLastname, studentShortLastname, studentLongLastname,
					studentSpecialCharLastname);
		}

		@ParameterizedTest
		@MethodSource("studentNameErrors")
		public void studentSave_error_1(Student studentNameError) {
			assertThrows(IllegalArgumentException.class, () -> {
				studentService.save(studentNameError);
			});
			verifyNoInteractions(studentRepository);
		}

		public void studentSave_error_2() {
			assertThrows(NullPointerException.class, () -> {
				studentService.save(studentNullName);
			});
			verifyNoInteractions(studentRepository);

		}

		@ParameterizedTest
		@MethodSource("studentLastnameErrors")
		public void studentSave_error_lastname(Student studentLastnameError) {

			assertThrows(IllegalArgumentException.class, () -> {
				studentService.save(studentLastnameError);
			});

			assertThrows(NullPointerException.class, () -> {
				studentService.save(studentNullLastname);
			});

			verifyNoInteractions(studentRepository);
		}
	}

	@Test
	public void studentFindAll_ok() {

		when(studentRepository.findAll()).thenReturn(listStudent);
		List<Student> resultado = studentService.findAll();
		assertEquals(listStudent, resultado);
		verify(studentRepository).findAll();

	}

	@Test
	public void studentFindById_withId() {

		when(studentRepository.findById(anyLong())).thenReturn(studentOptional);
		Optional<Student> resultado = studentService.findById(studentOptional.get().getId());
		assertEquals(studentOptional, resultado);
		verify(studentRepository).findById(anyLong());

	}

	@Nested
	class StudentFindById_Error {

		@Test
		public void studentFindById_IdNoExists() {

			when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());
			Optional<Student> resultado = studentService.findById(500L);
			assertEquals(Optional.empty(), resultado);
			verify(studentRepository).findById(500L);

		}

		@Test
		public void studentFindById_withoutId() {

			assertThrows(IllegalArgumentException.class, () -> {
				studentService.findById(null);
			});
			verifyNoInteractions(studentRepository);

		}
	}

	@Nested
	class StudentDeleteById_Error {

		@Test
		public void studentDeleteById_IdNoExists() {

			studentService.deleteById(500L);
			verify(studentRepository).deleteById(500L);

		}

		@Test
		public void studentDeleteById_withoutId() {

			assertThrows(IllegalArgumentException.class, () -> {
				studentService.deleteById(null);
			});
			verifyNoInteractions(studentRepository);

		}
	}

	@Test
	public void studentDeleteById_withId() {

		studentService.deleteById(1L);
		verify(studentRepository).deleteById(1L);

	}

}

// Verificar lo guardado con Argument Captor?