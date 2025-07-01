package com.example.tablas.mappers;

import java.util.List;

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
	@Mapping(target="surname", source="lastname")
	StudentDto toStudentDto(Student student);
	
	@Mapping(target="lastname", source="surname")
	@Mapping(target="country", ignore = true)
	Student toEntity(StudentDto studentDto);	
	
	List<StudentDto> toListStudentDto(List<Student> students);
	
	List<Student> toListStudent(List<StudentDto> studentsDto);
	
	/* @AfterMapping
	default void setFullName(@MappingTarget StudentDto studentDto, Student student) {
		studentDto.setFullName(student.getName() + " " + student.getLastname());
	}
	*/
}
	