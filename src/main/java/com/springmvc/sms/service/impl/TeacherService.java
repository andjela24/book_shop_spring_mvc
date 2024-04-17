package com.springmvc.sms.service.impl;

import com.springmvc.sms.dto.TeacherDto;
import com.springmvc.sms.dto.TeacherDto;
import com.springmvc.sms.entity.Teacher;
import com.springmvc.sms.entity.Teacher;
import com.springmvc.sms.excetion.DataNotValidException;
import com.springmvc.sms.excetion.ResourceNotFoundException;
import com.springmvc.sms.mapper.TeacherMapper;
import com.springmvc.sms.mapper.TeacherMapper;
import com.springmvc.sms.repository.TeacherRepository;
import com.springmvc.sms.service.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService implements ITeacherService {
    
    private final TeacherRepository teacherRepository;


    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        if (teacherDto.getFirstName().isEmpty() || teacherDto.getFirstName().length() <= 1) {
            throw new DataNotValidException("First Name must have 2 or more characters");
        }
        if (teacherDto.getLastName().isEmpty() || teacherDto.getLastName().length() <= 1) {
            throw new DataNotValidException("Last Name must have 2 or more characters");
        }
        if (teacherDto.getPhoneNumber().isEmpty()  || !teacherDto.getPhoneNumber().matches("^[0-9 ]+$")) {
            throw new DataNotValidException("Phone number is not in a valid format");
        }
        if (teacherDto.getEmail().isEmpty() || !teacherDto.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new DataNotValidException("Email must be in a valid format");
        }
        Teacher teacher = Teacher.builder()
                .firstName(teacherDto.getFirstName())
                .lastName(teacherDto.getLastName())
                .phoneNumber(teacherDto.getPhoneNumber())
                .email(teacherDto.getEmail())
                .build();

        teacherRepository.save(teacher);

        TeacherDto createdTeacherDto = TeacherMapper.mapToTeacherDto(teacher);
        return createdTeacherDto;
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        List<TeacherDto> foundTeachers = new ArrayList<>();
        List<Teacher> teachers = teacherRepository.findAll();

        if (teachers.isEmpty()) {
            throw new ResourceNotFoundException("List of teachers is empty");
        }

        for (Teacher teacher : teachers) {
            TeacherDto teacherDto = TeacherMapper.mapToTeacherDto(teacher);
            foundTeachers.add(teacherDto);
        }

        return foundTeachers;
    }

    @Override
    public TeacherDto getTeacherById(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new ResourceNotFoundException("Didn't find teacher with id:" + teacherId));
        TeacherDto teacherDto = TeacherMapper.mapToTeacherDto(teacher);
        return teacherDto;
    }

    @Override
    public TeacherDto updateTeacher(Long teacherId, TeacherDto teacherDto) {
        Teacher foundTeacher = teacherRepository.findById(teacherId).orElseThrow(() -> new ResourceNotFoundException("Entity whit id " + teacherId + " could not be updated"));

        boolean isChanged = false;

        if (teacherDto.getFirstName().isEmpty() || teacherDto.getFirstName().length() <= 1) {
            throw new DataNotValidException("First Name must have 2 or more characters");
        } else {
            isChanged = true;
            foundTeacher.setFirstName(teacherDto.getFirstName());
        }
        if (teacherDto.getLastName().isEmpty() || teacherDto.getLastName().length() <= 1) {
            throw new DataNotValidException("Last Name must have 2 or more characters");
        } else {
            isChanged = true;
            foundTeacher.setLastName(teacherDto.getLastName());
        }
        if (teacherDto.getPhoneNumber().isEmpty()  || !teacherDto.getPhoneNumber().matches("^[0-9 ]+$")) {
            throw new DataNotValidException("Phone number is not in a valid format");
        }else {
            isChanged = true;
            foundTeacher.setPhoneNumber(teacherDto.getPhoneNumber());
        }
        if (teacherDto.getEmail().isEmpty() || !teacherDto.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new DataNotValidException("Email must be in a valid format");
        }else {
            isChanged = true;
            foundTeacher.setEmail(teacherDto.getEmail());
        }

        if (isChanged) {
            foundTeacher.setUpdatedAt(LocalDateTime.now());
            teacherRepository.save(foundTeacher);
        }
        TeacherDto updatedTeacherDto = TeacherMapper.mapToTeacherDto(foundTeacher);

        return updatedTeacherDto;
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        if (!teacherRepository.existsById(teacherId)) {
            throw new ResourceNotFoundException("Didn't find teacher with id:" + teacherId);
        }
        teacherRepository.deleteById(teacherId);
    }
}
