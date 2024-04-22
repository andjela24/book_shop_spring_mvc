package com.springmvc.sms.mapper;

import com.springmvc.sms.dto.AttendanceDto;
import com.springmvc.sms.entity.Attendance;

public class AttendanceMapper {
    //    public static AttendanceDto mapToAttendanceDto(Attendance attendance) {
//        AttendanceDto attendanceDto = new AttendanceDto(
//                attendance.getStudent(),
//                attendance.getLesson(),
//                attendance.isPresent()
//        );
//        return attendanceDto;
//    }
    public static AttendanceDto mapToAttendanceDto(Attendance attendance) {
        AttendanceDto attendanceDto = new AttendanceDto();
        attendanceDto.setId(attendance.getId());
        attendanceDto.setStudent(attendance.getStudent());
        attendanceDto.setLesson(attendance.getLesson());
        attendanceDto.setPresent(attendance.isPresent());
        return attendanceDto;
    }

    public static Attendance mapToAttendance(AttendanceDto attendanceDto) {
        Attendance attendance = new Attendance(
                attendanceDto.getId(),
                attendanceDto.getStudent(),
                attendanceDto.getLesson(),
                attendanceDto.isPresent()
        );
        return attendance;
    }
}
