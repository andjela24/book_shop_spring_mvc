package com.springmvc.sms.mapper;

import com.springmvc.sms.dto.StudentDto;
import com.springmvc.sms.entity.Student;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Student student){
        StudentDto studentDto = new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getSubjects()
        );
        return studentDto;
    }

    public static Student mapToStudent(StudentDto studentDto){
        Student student = new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getEmail(),
                studentDto.getSubjects()
        );
        return student;
    }
}
