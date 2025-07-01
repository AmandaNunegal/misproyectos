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

	public Student save(Student student) {

		return studentRepository.save(student);

	}

	
	/* public List<Student> saveAll(List<Student> listStudent) {

		return studentRepository.saveAll(listStudent);
		

	} */

	public void deleteById(Long id) {

		studentRepository.deleteById(id);

	}

	public Optional<Student> findById(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("El id no puede ser nulo");
		}
		return studentRepository.findById(id);

	}

}
