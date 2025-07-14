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

		if (student.getName().isBlank() || student.getLastname().isBlank()) {

			throw new IllegalArgumentException("El nombre y el apellido no pueden estar vacíos");
		}

		if (student.getName() == null || student.getLastname() == null) {
			throw new NullPointerException("El nombre y el apellido no pueden estar vacíos");
		}

		if ((student.getName().length() < 3 || student.getName().length() > 20)
				|| (student.getLastname().length() < 3 || student.getLastname().length() > 20)) {
			throw new IllegalArgumentException("El nombre y el apellido deben tener entre 3 y 20 caracteres");
		}
		return studentRepository.save(student);

	}

	public void deleteById(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("El id no puede ser nulo");
		}
		studentRepository.deleteById(id);

	}

	public Optional<Student> findById(Long id) {

		if (id == null) {
			throw new IllegalArgumentException("El id no puede ser nulo");
		}
		return studentRepository.findById(id);

	}

}
