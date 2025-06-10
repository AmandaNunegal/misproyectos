package com.example.tablas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String lastname;
	private String country;

	public Student() {
		
	}
	
	public Student(String name, String lastname) {
		this.name = name;
		this.lastname = lastname;
		this.country = null;
	}
}
