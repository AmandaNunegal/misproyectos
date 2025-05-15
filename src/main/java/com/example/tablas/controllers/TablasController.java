package com.example.tablas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tablas.entity.Student;
import com.example.tablas.repository.StudentRepository;

@Controller
public class TablasController {

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/")
	public String passStudentData(Model model) {

		List<Student> students = studentRepository.findAll();
		model.addAttribute("students", students);
		model.addAttribute("st", new Student());
		return "index";

	}

	@PostMapping("/newSt")
	public String saveNewStudent(@ModelAttribute Student student, Model model) {

		studentRepository.save(student);
		return "redirect:/";
	}

	/*
	 * @PostMapping(value="/deleteSt") public String
	 * deleteStudent(@RequestParam("idStudent") String[] name, Model model) {
	 * 
	 * String[] nameNew = name; studentRepository.deleteById(nameNew[1]);
	 * 
	 * return ("redirect:/"); }
	 */
	
	@PostMapping("/deleteSt")
	
	public String deleteStudent(@RequestParam("idStudent") String id, Model model) {
	 
	 studentRepository.deleteById(Long.parseLong(id));
	 
	 return ("redirect:/"); }
	
	/*
	@PostMapping(value="/deleteSt")
	public String deleteStudent(@ModelAttribute Student student, Model model) {
				
		studentRepository.deleteById((long)student.getId());
		
		return ("redirect:/");
		
	} */

}
