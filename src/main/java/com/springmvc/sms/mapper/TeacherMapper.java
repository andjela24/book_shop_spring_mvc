package com.springmvc.sms.mapper;

import com.springmvc.sms.dto.TeacherDto;
import com.springmvc.sms.entity.Teacher;

public class TeacherMapper {

    public static TeacherDto mapToTeacherDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getPhoneNumber(),
                teacher.getEmail(),
                teacher.getSubjects()
        );
        return teacherDto;
    }
    public static Teacher mapToTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher(
                teacherDto.getId(),
                teacherDto.getFirstName(),
                teacherDto.getLastName(),
                teacherDto.getPhoneNumber(),
                teacherDto.getEmail(),
                teacherDto.getSubjects()
        );
        return teacher;
    }
}
