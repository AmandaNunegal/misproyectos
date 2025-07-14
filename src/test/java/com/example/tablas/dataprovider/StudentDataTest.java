package com.example.tablas.dataprovider;

import java.util.List;

import com.example.tablas.entity.Student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDataTest {

	private Student studentComplete;
	private Student studentWithoutCountry;
	private Student studentEmpty;
	private Student studentNull;
	private Student studentSpaces;
	private Student studentShort;
	private Student studentLong;
	private Student studentSpecialChar;
	private Student studentLimitMin;
	private Student studentLimitMax;
	private List<Student> listStudent;
}
