package com.example.tablas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Student {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String lastname;
	private String country;

}
