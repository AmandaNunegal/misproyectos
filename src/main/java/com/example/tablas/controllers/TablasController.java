package com.example.tablas.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.tablas.entity.Student;
import com.example.tablas.services.StudentService;

@Controller
public class TablasController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/")
	public String passStudentData(Model model) {
		
		List<Student> students = studentService.findAll();
		model.addAttribute("students", students);
		model.addAttribute("st", new Student());
				
		return "index";

	}

	@PostMapping("/newSt")
	public String saveNewStudent(@ModelAttribute Student student, Model model) {

		studentService.save(student);
		return "redirect:/";
	}

	
	@PostMapping("/deleteSt")	
	public String deleteStudent(@RequestBody Student st) {

	 studentService.deleteById((long)st.getId());
	 return "redirect:/";
	 
	 }
	
	/*@PostMapping("/editSt")
	public String editStudent(@ModelAttribute Student student, Model model) {
		
		studentService.findById(student);
		model.addAttribute("student", students);
		model.addAttribute("st", new Student());
		studentService.findById(student);
				
		return "redirect:/";
		
	} */

}
