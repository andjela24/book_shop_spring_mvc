package com.springmvc.sms.service;

import com.springmvc.sms.dto.AttendanceDto;
import com.springmvc.sms.entity.Attendance;

import java.util.List;

public interface IAttendanceService {
    AttendanceDto createAttendance(AttendanceDto attendanceDto);
    List<AttendanceDto> getAllAttendances();
    AttendanceDto getAttendanceById(Long attendanceId);
    AttendanceDto updateAttendance(AttendanceDto attendanceDto);
    void deleteAttendance(Long attendanceId);
}
