package com.springmvc.sms.service;

import com.springmvc.sms.dto.TeacherDto;
import com.springmvc.sms.entity.Teacher;

import java.util.List;

public interface ITeacherService {
    TeacherDto createTeacher(TeacherDto teacherDto);
    List<TeacherDto> getAllTeachers();
    TeacherDto getTeacherById(Long teacherId);
    TeacherDto updateTeacher(Long teacherId, TeacherDto teacherDto);
    void deleteTeacher(Long teacherId);
}
