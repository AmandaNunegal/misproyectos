package com.example.tablas.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.tablas.DataProvider;
import com.example.tablas.dtos.StudentDto;
import com.example.tablas.entity.Student;
import com.example.tablas.repository.StudentRepository;

//when(studentService.findById(1L)).thenReturn(student);
//when(studentService.findById(500L)).thenReturn(null);

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

	private static List<Student> listStudentMock;
	private static List<StudentDto> listStudentDtoMock;
	private static Student student1Mock;
	private static StudentDto studentDto1Mock;
	private static Student student1IdNullMock;
	private static StudentDto studentDto1IdNullMock;
	private static Student student2Mock;
	private static StudentDto studentDto2Mock;
	private static Optional<Student> studentOptionalMock;

	@InjectMocks
	private StudentService studentService;

	@Mock
	private StudentRepository studentRepository;

	@BeforeEach
	public void setUp() {

		listStudentMock = DataProvider.listStudentMock();
		listStudentDtoMock = DataProvider.listStudentDtoMock();
		student1Mock = DataProvider.student1Mock();
		studentDto1Mock = DataProvider.studentDto1Mock();
		student1IdNullMock = DataProvider.student1IdNullMock();
		studentDto1IdNullMock = DataProvider.studentDto1IdNullMock();
		student2Mock = DataProvider.student2Mock();
		studentDto2Mock = DataProvider.studentDto2Mock();
		studentOptionalMock = DataProvider.studentOptionalMock();
	}

	@Test
	public void StudentSave_ok() {

		when(studentRepository.save(student1Mock)).thenReturn(student1Mock);
		Student resultado = studentService.save(student1Mock);
		assertEquals(student1Mock, resultado);
		verify(studentRepository, times(1)).save(student1Mock);

	}

	@Test
	public void StudentFindAll() {

		when(studentRepository.findAll()).thenReturn(listStudentMock);
		List<Student> resultado = studentService.findAll();
		assertEquals(listStudentMock, resultado);
		verify(studentRepository, times(1)).findAll();

	}

	@Test
	public void StudentFindById_withId() {

		when(studentRepository.findById(student1Mock.getId())).thenReturn(studentOptionalMock);
		Optional<Student> resultado = studentService.findById(studentOptionalMock.get().getId());
		assertEquals(studentOptionalMock, resultado);
		verify(studentRepository, times(1)).findById(student1Mock.getId());

	}

	@Test
	public void StudentFindById_withoutId() {

		assertThrows(IllegalArgumentException.class, () -> {
			studentService.findById(null);
		});
		verifyNoInteractions(studentRepository);

	}

	@Test
	public void StudentDeleteById_withId() {

		studentService.deleteById(student1Mock.getId());
		verify(studentRepository, times(1)).deleteById(student1Mock.getId());

	}

// Verificar lo guardado con Argument Captor?
	/*
	 * // PREGUNTAR
	 * 
	 * @Test public void StudentFindById_IdNull() {
	 * 
	 * assertThrows(IllegalArgumentException.class, () -> {
	 * studentService.findById(null); }); verify(studentRepository,
	 * times(0)).findById(null);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Test public void StudentDeleteById_withoutId() {
	 * 
	 * studentService.deleteById(-1L); verify(studentRepository,
	 * times(0)).deleteById(DataProvider.student1Mock().getId());
	 * 
	 * }
	 */

	/*
	 * //------- // BORRAR - @Test public void StudentSave_null() {
	 * 
	 * when(studentRepository.save(DataProvider.studentNullMock())).thenReturn(null)
	 * ; Student resultado = studentService.save(DataProvider.studentNullMock());
	 * assertEquals(DataProvider.studentNullMock(), resultado);
	 * verify(studentRepository, times(1)).save(DataProvider.studentNullMock());
	 * 
	 * }
	 * 
	 * // BORRAR - @Test public void StudentSave_empty() {
	 * 
	 * when(studentRepository.save(DataProvider.studentEmptyMock())).thenReturn(
	 * DataProvider.studentEmptyMock()); Student resultado =
	 * studentService.save(DataProvider.studentEmptyMock());
	 * assertEquals(DataProvider.studentEmptyMock(), resultado);
	 * verify(studentRepository, times(1)).save(DataProvider.studentEmptyMock());
	 * 
	 * }
	 * 
	 */
	// PREGUNTAR

	// Si el tamaño de la lista es 0...
	// Guardar un estudiante vacío
	// Guardar un estudiante null?
	// Guardar Lista vacía
	// Guardar lista null?
	// Borrar id null
}
