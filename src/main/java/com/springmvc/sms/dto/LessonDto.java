package com.springmvc.sms.dto;

import com.springmvc.sms.entity.Subject;
import com.springmvc.sms.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {
    private Long id;
    private LocalDateTime date;
    private Teacher teacher;
    private Subject subject;
}
