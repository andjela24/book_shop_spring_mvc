package com.springmvc.sms.service;

import com.springmvc.sms.dto.StudentDto;

import java.util.List;

public interface IStudentService {
    StudentDto createStudent(StudentDto student);
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long studentId);
    StudentDto updateStudent(StudentDto studentDto);
    void deleteStudent(Long studentId);

}
