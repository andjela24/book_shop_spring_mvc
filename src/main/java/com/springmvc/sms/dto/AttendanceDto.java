package com.springmvc.sms.dto;

import com.springmvc.sms.entity.Lesson;
import com.springmvc.sms.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDto {
    private Student student;
    private Lesson lesson;
    private boolean isPresent;
}
