package com.example.tablas.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.tablas.dataprovider.DataProvider;
import com.example.tablas.dtos.StudentDto;
import com.example.tablas.entity.Student;
import com.example.tablas.mappers.StudentMapper;
import com.example.tablas.services.StudentService;

@WebMvcTest(TablasController.class)
public class TablasControllerTest {

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
	private static Student studentShortLastame;
	private static Student studentLongName;
	private static Student studentLongLastname;
	private static Student studentSpecialCharName;
	private static Student studentSpecialCharLastname;
	private static Student studentLimitMinName;
	private static Student studentLimitMinLastname;
	private static Student studentLimitMaxName;
	private static Student studentLimitMaxLastname;

	@Autowired
	private MockMvc mockMvc;	

	@MockitoBean
	private StudentService studentService;

	@MockitoBean
	private StudentMapper studentMapper;

	@BeforeEach
	public void setUp() {

		listStudent = DataProvider.listStudent();
		listStudentDto = DataProvider.listStudentDto();
		studentComplete = DataProvider.studentComplete();
		studentDto1 = DataProvider.studentDto1();
		student1IdNull = DataProvider.student1IdNull();
		studentDto1IdNull = DataProvider.studentDto1IdNull();
		student2 = DataProvider.student2();
		studentDto2 = DataProvider.studentDto2();
		studentOptional = DataProvider.studentOptional();
		studentEmptyName = DataProvider.studentEmptyName();
		studentEmptyLastname = DataProvider.studentEmptyLastname();
		studentNullName = DataProvider.studentNullLastname();
		studentNullLastname = DataProvider.studentNullLastname();
		studentSpacesName = DataProvider.studentSpacesName();
		studentSpacesName = DataProvider.studentSpacesLastname();
		studentShortName = DataProvider.studentShortName();
		studentShortLastame = DataProvider.studentShortLastname();
		studentLongName = DataProvider.studentLongName();
		studentLongLastname = DataProvider.studentLongLastname();
		studentSpecialCharName = DataProvider.studentSpecialCharName();
		studentSpecialCharLastname = DataProvider.studentSpecialCharLastname();
		studentLimitMinName = DataProvider.studentLimitMinName();
		studentLimitMinLastname = DataProvider.studentLimitMinLastname();
		studentLimitMaxName = DataProvider.studentLimitMaxName();
		studentLimitMaxLastname = DataProvider.studentLimitMaxLastname();;
	}

	@Test
	public void saveStudentGet_ok() throws Exception {

		when(studentService.findAll()).thenReturn(listStudent);
		when(studentMapper.toListStudentDto(listStudent)).thenReturn(listStudentDto);

		mockMvc.perform(get("/").contentType(MediaType.TEXT_HTML))
				.andExpect(model().attribute("students", instanceOf(List.class)))
				.andExpect(model().attribute("st", instanceOf(StudentDto.class)))
				.andExpect(model().attribute("std", instanceOf(StudentDto.class)))
				.andExpect(model().attribute("students", equalTo(listStudentDto)))
				.andExpect(view().name("index"))
				.andExpect(status().isOk());

		verify(studentService, times(1)).findAll();
	}

	@Test
	public void saveStudentPost_ok() throws Exception {

		when(studentService.save(student1IdNull)).thenReturn(student1IdNull);
		when(studentMapper.toEntity(studentDto1IdNull)).thenReturn(student1IdNull);

		mockMvc.perform(post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("name", studentDto1IdNull.getName()).param("surname", studentDto1IdNull.getSurname()))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));
		verify(studentService, times(1)).save(student1IdNull);
	}

	@Test
	public void saveStudentPost_errorParam() throws Exception {

		when(studentService.save(student1IdNull)).thenReturn(student1IdNull);
		when(studentMapper.toEntity(studentDto1IdNull)).thenReturn(student1IdNull);

		mockMvc.perform(
				post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("name", "").param("surname", ""))
				.andExpect(model().attributeExists("students")).andExpect(model().attributeExists("std"))
				.andExpect(model().attribute("students", instanceOf(List.class)))
				.andExpect(model().attribute("std", instanceOf(StudentDto.class)))
				.andExpect(model().attributeHasFieldErrors("st", "name", "surname")).andExpect(view().name("index"))
				.andExpect(status().isOk());

		verify(studentService, times(0)).save(student1IdNull);
	}

	@Test
	public void deleteStudent() throws Exception {

		mockMvc.perform(post("/deleteSt").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id",
				String.valueOf(studentComplete.getId()))).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));
		verify(studentService, times(1)).deleteById(studentComplete.getId());
	}

	@Test
	public void editStudent() throws Exception {

		when(studentService.save(any(Student.class))).thenReturn(student2);
		when(studentMapper.toEntity(studentDto2)).thenReturn(student2);

		mockMvc.perform(post("/editSt").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", String.valueOf(studentDto2.getId())).param("name", studentDto2.getName())
				.param("surname", studentDto2.getSurname())).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));
		verify(studentService).save(any(Student.class));
	}

}
/*
 * @Test public void newStudentGet() throws Exception {
 * 
 * studentRepository.saveAll(DataProvider.listStudentMock());
 * 
 * List<StudentDto> studentsToCheck =
 * studentMapper.toListStudentDto(DataProvider.listStudentMock());
 * 
 * mockMvc.perform(get("/").contentType(MediaType.TEXT_HTML))
 * .andExpect(model().attribute("students", instanceOf(ArrayList.class)))
 * .andExpect(model().attribute("std", instanceOf(StudentDto.class)))
 * .andExpect(view().name("index")) .andExpect(status().isOk());
 * //.andExpect(model().attribute("students",
 * studentsToCheck).equals(studentsToCheck)) //
 * .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/")); }
 */
