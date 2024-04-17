package com.springmvc.sms.service.impl;

import com.springmvc.sms.dto.AttendanceDto;
import com.springmvc.sms.entity.Attendance;
import com.springmvc.sms.excetion.DataNotValidException;
import com.springmvc.sms.excetion.ResourceNotFoundException;
import com.springmvc.sms.mapper.AttendanceMapper;
import com.springmvc.sms.repository.AttendanceRepository;
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

    @Override
    public AttendanceDto createAttendance(AttendanceDto attendanceDto) {
        if (attendanceDto.getStudent() == null) {
            throw new DataNotValidException("Student must exists");
        }
        if (attendanceDto.getLesson() == null) {
            throw new DataNotValidException("Lesson must exists");
        }
        Attendance attendance = Attendance.builder()
                .student(attendanceDto.getStudent())
                .lesson(attendanceDto.getLesson())
                .isPresent(attendanceDto.isPresent())
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
    public AttendanceDto updateAttendance(Long attendanceId, AttendanceDto attendanceDto) {
        Attendance foundAttendance = attendanceRepository.findById(attendanceId).orElseThrow(() -> new ResourceNotFoundException("Entity whit id " + attendanceId + " could not be updated"));

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
