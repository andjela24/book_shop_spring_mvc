package com.springmvc.sms.dto;

import com.springmvc.sms.entity.Lesson;
import com.springmvc.sms.entity.Student;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDto {
    private Long id;
    private Student student;
    private Lesson lesson;
    private boolean present;
}
