package com.springmvc.sms.service.impl;

import com.springmvc.sms.dto.StudentDto;
import com.springmvc.sms.entity.Student;
import com.springmvc.sms.excetion.DataNotValidException;
import com.springmvc.sms.excetion.ResourceNotFoundException;
import com.springmvc.sms.mapper.StudentMapper;
import com.springmvc.sms.repository.StudentRepository;
import com.springmvc.sms.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        //Prvo validacija
        if (studentDto.getFirstName().isEmpty() || studentDto.getFirstName().length() <= 1) {
            throw new DataNotValidException("First Name must have 2 or more characters");
        }
        if (studentDto.getLastName().isEmpty() || studentDto.getLastName().length() <= 1) {
            throw new DataNotValidException("Last Name must have 2 or more characters");
        }
        if (studentDto.getEmail().isEmpty() || !studentDto.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new DataNotValidException("Email must be in a valid format");
        }
        //Kreiranje instance Student moze sa builder
        Student student = Student.builder()
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .email(studentDto.getEmail())
                .build();

        // Moze sa setterima
//        Student student = new Student();
//        student.setFirstName(studentDto.getFirstName());
//        student.setLastName(studentDto.getLastName());
//        student.setEmail(studentDto.getEmail());

        //Cuva se u bazi
        studentRepository.save(student);

        //Mapiramo u DTO
        StudentDto createdStudentDto = StudentMapper.mapToStudentDto(student);
        return createdStudentDto;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentDto> foundStudents = new ArrayList<>();
        List<Student> students = studentRepository.findAll();

        if (students.isEmpty()) {
            throw new ResourceNotFoundException("List of students is empty");
        }

        for (Student student : students) {
            StudentDto studentDto = StudentMapper.mapToStudentDto(student);
            foundStudents.add(studentDto);
        }

        return foundStudents;
    }


    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Didn't find student with id:" + studentId));
        StudentDto studentDto = StudentMapper.mapToStudentDto(student);
        return studentDto;
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        Student foundStudent = studentRepository.findById(studentDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Entity whit id " + studentDto.getId() + " could not be updated"));
        Student student = StudentMapper.mapToStudent(studentDto);

        boolean isChanged = false;
        if (student.getFirstName().length() < 2) {
            throw new DataNotValidException("First Name must have 2 or more characters");
        } else {
            isChanged = true;
            foundStudent.setFirstName(studentDto.getFirstName());
        }
        if (student.getLastName().length() < 2) {
            throw new DataNotValidException("Last Name must have 2 or more characters");
        } else {
            isChanged = true;
            foundStudent.setLastName(studentDto.getLastName());
        }
        if (!student.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new DataNotValidException("Email must be in a valid format");
        } else {
            isChanged = true;
            foundStudent.setEmail(studentDto.getEmail());
        }

        if (isChanged) {
            foundStudent.setUpdatedAt(LocalDateTime.now());
            studentRepository.save(foundStudent);
        }
        StudentDto updatedStudentDto = StudentMapper.mapToStudentDto(foundStudent);

        return updatedStudentDto;
    }

    @Override
    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Didn't find student with id:" + studentId);
        }
        studentRepository.deleteById(studentId);
    }
}
