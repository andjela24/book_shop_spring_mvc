package com.springmvc.sms.service;

import com.springmvc.sms.dto.StudentDto;

import java.util.List;

public interface IStudentService {
    StudentDto createStudent(StudentDto student);
    //Moze da bude i void ali ja vise volim da vratim nesto kao rezultat metode
    //    void createStudent(StudentDto student);
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long studentId);
    //    void updateStudent(StudentDto studentDto);
    StudentDto updateStudent(StudentDto studentDto);
    //    void deleteStudent(Long studentId);
    void deleteStudent(Long studentId);

}
