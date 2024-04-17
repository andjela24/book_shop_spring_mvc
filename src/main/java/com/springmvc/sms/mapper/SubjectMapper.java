package com.springmvc.sms.mapper;

import com.springmvc.sms.dto.SubjectDto;
import com.springmvc.sms.entity.Subject;

public class SubjectMapper {
    public static SubjectDto mapToSubjectDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto(
                subject.getName()
        );
        return subjectDto;
    }

    public static Subject mapToSubject(SubjectDto subjectDto) {
        Subject subject = new Subject(
                subjectDto.getName()
        );
        return subject;
    }
}
