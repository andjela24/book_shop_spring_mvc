package com.springmvc.sms.service.impl;

import com.springmvc.sms.dto.AttendanceDto;
import com.springmvc.sms.entity.Attendance;
import com.springmvc.sms.entity.Lesson;
import com.springmvc.sms.entity.Student;
import com.springmvc.sms.excetion.DataNotValidException;
import com.springmvc.sms.excetion.ResourceNotFoundException;
import com.springmvc.sms.mapper.AttendanceMapper;
import com.springmvc.sms.repository.AttendanceRepository;
import com.springmvc.sms.repository.LessonRepository;
import com.springmvc.sms.repository.StudentRepository;
import com.springmvc.sms.service.IAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService implements IAttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    @Override
    public AttendanceDto createAttendance(AttendanceDto attendanceDto) {
//        if (attendanceDto.getStudent() == null) {
//            throw new DataNotValidException("Student must exists");
//        }
//        if (attendanceDto.getLesson() == null) {
//            throw new DataNotValidException("Lesson must exists");
//        }

        Student student = studentRepository.findById(attendanceDto.getStudent().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + attendanceDto.getStudent().getId()));

        Lesson lesson = lessonRepository.findById(attendanceDto.getLesson().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with ID: " + attendanceDto.getLesson().getId()));

        Attendance attendance = Attendance.builder()
                .student(student)
                .lesson(lesson)
                .isPresent(attendanceDto.isPresent())
                .createdAt(LocalDateTime.now())
                .build();

        attendanceRepository.save(attendance);

        AttendanceDto createdAttendanceDto = AttendanceMapper.mapToAttendanceDto(attendance);
        return createdAttendanceDto;
    }

    @Override
    public List<AttendanceDto> getAllAttendances() {
        List<AttendanceDto> foundAttendances = new ArrayList<>();
        List<Attendance> attendances = attendanceRepository.findAll();

        if (attendances.isEmpty()) {
            throw new ResourceNotFoundException("List of attendances is empty");
        }

        for (Attendance attendance : attendances) {
            AttendanceDto attendanceDto = AttendanceMapper.mapToAttendanceDto(attendance);
            attendanceDto.setStudent(attendance.getStudent());
            attendanceDto.setLesson(attendance.getLesson());
            attendanceDto.setPresent(attendance.isPresent());
            foundAttendances.add(attendanceDto);
        }

        return foundAttendances;
    }

    @Override
    public AttendanceDto getAttendanceById(Long attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId).orElseThrow(() -> new ResourceNotFoundException("Didn't find attendance with id:" + attendanceId));
        AttendanceDto attendanceDto = AttendanceMapper.mapToAttendanceDto(attendance);
        return attendanceDto;
    }

    @Override
    public AttendanceDto updateAttendance(AttendanceDto attendanceDto) {
        Attendance foundAttendance = attendanceRepository.findById(attendanceDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Entity whit id " + attendanceDto.getId() + " could not be updated"));

        boolean isChanged = false;

        if (attendanceDto.getStudent() == null) {
            throw new DataNotValidException("Student must exists");
        } else {
            isChanged = true;
            foundAttendance.setStudent(attendanceDto.getStudent());
        }
        if (attendanceDto.getLesson() == null) {
            throw new DataNotValidException("Lesson must exists");
        } else {
            isChanged = true;
            foundAttendance.setLesson(attendanceDto.getLesson());
        }

        if (isChanged) {
            foundAttendance.setUpdatedAt(LocalDateTime.now());
            attendanceRepository.save(foundAttendance);
        }
        AttendanceDto updatedAttendanceDto = AttendanceMapper.mapToAttendanceDto(foundAttendance);

        return updatedAttendanceDto;
    }

    @Override
    public void deleteAttendance(Long attendanceId) {
        if (!attendanceRepository.existsById(attendanceId)) {
            throw new ResourceNotFoundException("Didn't find attendance with id:" + attendanceId);
        }
        attendanceRepository.deleteById(attendanceId);
    }
}
