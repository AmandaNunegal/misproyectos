package com.example.tablas.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.tablas.DataProvider;
import com.example.tablas.dtos.StudentDto;
import com.example.tablas.entity.Student;
import com.example.tablas.mappers.StudentMapper;
import com.example.tablas.services.StudentService;

@WebMvcTest(TablasController.class)
public class TablasControllerTest {

	private static List<Student> listStudentMock;
	private static List<StudentDto> listStudentDtoMock;
	private static Student student1Mock;
	private static StudentDto studentDto1Mock;
	private static Student student1IdNullMock;	
	private static StudentDto studentDto1IdNullMock;
	private static Student student2Mock;
	private static StudentDto studentDto2Mock;

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private StudentService studentService;

	@MockitoBean
	private StudentMapper studentMapper;

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
	}

	@Test
	public void newStudentGet() throws Exception {

		when(studentService.findAll()).thenReturn(listStudentMock);
		when(studentMapper.toListStudentDto(listStudentMock)).thenReturn(listStudentDtoMock);

		mockMvc.perform(get("/").contentType(MediaType.TEXT_HTML))
				.andExpect(model().attribute("students", instanceOf(List.class)))
				.andExpect(model().attribute("st", instanceOf(StudentDto.class)))				
				.andExpect(model().attribute("std", instanceOf(StudentDto.class)))				
				.andExpect(model().attribute("students", equalTo(listStudentDtoMock)))				
				.andExpect(view().name("index"))
				.andExpect(status().isOk());

		verify(studentService, times(1)).findAll();
	}

	@Test
	public void newStudentPost_ok() throws Exception {

		when(studentService.save(student1IdNullMock)).thenReturn(student1IdNullMock);
		when(studentMapper.toEntity(studentDto1IdNullMock)).thenReturn(student1IdNullMock);

		mockMvc.perform(post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("name", studentDto1IdNullMock.getName())
				.param("surname", studentDto1IdNullMock.getSurname()))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));
		verify(studentService, times(1)).save(student1IdNullMock);
	}

	@Test
	public void newStudentPost_error_param() throws Exception {

		when(studentService.save(student1IdNullMock)).thenReturn(student1IdNullMock);
		when(studentMapper.toEntity(studentDto1IdNullMock)).thenReturn(student1IdNullMock);

		mockMvc.perform(post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("name", "")
				.param("surname", ""))				
				.andExpect(model().attributeExists("students"))
				.andExpect(model().attributeExists("std"))
				.andExpect(model().attribute("students", instanceOf(List.class)))
				.andExpect(model().attribute("std", instanceOf(StudentDto.class)))	
				.andExpect(model().attributeHasFieldErrors("st", "name", "surname"))
				.andExpect(view().name("index"))
				.andExpect(status().isOk());
				
		verify(studentService, times(0)).save(student1IdNullMock);
	}

	@Test
	public void deleteStudent() throws Exception {

		mockMvc.perform(post("/deleteSt")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", String.valueOf(student1Mock.getId())))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/"));
		verify(studentService, times(1)).deleteById(student1Mock.getId());
	}

	@Test
	public void editStudent() throws Exception {
		
		when(studentService.save(student2Mock)).thenReturn(student2Mock);
		when(studentMapper.toEntity(studentDto2Mock)).thenReturn(student2Mock);
		
		mockMvc.perform(post("/editSt")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", String.valueOf(studentDto2Mock.getId()))
				.param("name", studentDto2Mock.getName())
				.param("surname", studentDto2Mock.getSurname()))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl("/"));
		verify(studentService, times(1)).save(student2Mock);
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
