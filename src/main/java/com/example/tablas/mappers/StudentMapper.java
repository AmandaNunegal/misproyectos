package com.example.tablas.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.example.tablas.dtos.StudentDto;
import com.example.tablas.entity.Student;

@Mapper
public interface StudentMapper {

	StudentMapper mapper = Mappers.getMapper(StudentMapper.class);
	
	/* @Mapping(target="fullName", ignore = true) */
	StudentDto toStudentDto(Student student);
	
	/* @AfterMapping
	default void setFullName(@MappingTarget StudentDto studentDto, Student student) {
		studentDto.setFullName(student.getName() + " " + student.getLastname());
	}
	*/
}
	