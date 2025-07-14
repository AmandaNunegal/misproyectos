package com.example.tablas.dataprovider;

import java.io.File;
import java.io.IOException;

import com.example.tablas.entity.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataProvider2 {

	private static StudentDataTest studentDataTest;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	public void myMapper() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("data/testData.json").getFile());
			StudentDataTest studentDT = objectMapper.readValue(file, StudentDataTest.class);
			System.out.println(studentDT.getStudentComplete());
		} catch (IOException e ) {
			e.printStackTrace();
		}
		
	}
}