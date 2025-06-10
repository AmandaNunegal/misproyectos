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

		studentRepository.save(student);
		return student;

	}

	public void deleteById(long id) {

		studentRepository.deleteById(id);

	}

	public Student findById(long id) {

		Optional<Student> st = studentRepository.findById(id);
		if (st.isPresent()) {
			return st.get();
		}
		return null;

	}

}
