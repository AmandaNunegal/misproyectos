package com.example.tablas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.tablas.dtos.StudentDto;
import com.example.tablas.entity.Student;
import com.example.tablas.mappers.StudentMapper;
import com.example.tablas.services.StudentService;

import jakarta.validation.Valid;

@Controller
public class TablasController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/")
	public String findAllStudents(Model model) {

		List<StudentDto> students = StudentMapper.mapper.toListStudentDto(studentService.findAll());
		model.addAttribute("students", students);
		model.addAttribute("st", new StudentDto());
		model.addAttribute("std", new StudentDto());

		return "index";

	}

	@PostMapping("/")
	public String saveNewStudent(@Valid @ModelAttribute("st") StudentDto stDto, BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			List<StudentDto> students = StudentMapper.mapper.toListStudentDto(studentService.findAll());
			model.addAttribute("students", students);
			model.addAttribute("std", new StudentDto());			

			return "index";

		}

		studentService.save(StudentMapper.mapper.toEntity(stDto));

		return "redirect:/";
	}

	@PostMapping("/deleteSt") //RequestBody
	public String deleteStudent(@ModelAttribute StudentDto stDto) {

		studentService.deleteById((long) (stDto.getId()));
		return "redirect:/";

	}

	@PostMapping("/editSt")
	public String editStudent(@ModelAttribute("std") StudentDto stDto) { //Model model

		studentService.save(StudentMapper.mapper.toEntity(stDto));
		return "redirect:/";
	}
	
	// model.addAttribute("errors", bindingResult.getAllErrors());
	// return "redirect:/index?anchor='formEditModal'";
}
