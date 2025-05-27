package com.example.tablas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tablas.entity.Student;
import com.example.tablas.repository.StudentRepository;

@Service
public class StudentService {

	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> findAll() {
				
		return studentRepository.findAll();
	}
	
	public void save(Student student) {
		
		studentRepository.save(student);
		
	}
	
	public void deleteById(long id) {
				
		studentRepository.deleteById(id);		
		
	}
	
	public Student findById(Student student) {
		
		Optional<Student> st = studentRepository.findById(student.getId());
		if (st.isPresent()) {
			return st.get();
			//studentRepository.save(st.get());
		}
		return null;
		
	}
}
