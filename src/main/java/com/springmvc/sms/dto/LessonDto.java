package com.springmvc.sms.dto;

import com.springmvc.sms.entity.Subject;
import com.springmvc.sms.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {
    private Date date;
    private Teacher teacher;
    private Subject subject;
}
