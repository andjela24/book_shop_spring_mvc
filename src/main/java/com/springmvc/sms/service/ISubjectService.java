package com.springmvc.sms.service;

import com.springmvc.sms.dto.AttendanceDto;
import com.springmvc.sms.dto.SubjectDto;
import com.springmvc.sms.entity.Attendance;
import com.springmvc.sms.entity.Subject;

import java.util.List;

public interface ISubjectService {
    SubjectDto createSubject(SubjectDto subjectDto);
    List<SubjectDto> getAllSubjects();
    SubjectDto getSubjectById(Long subjectId);
    SubjectDto updateSubject(Long subjectId, SubjectDto subjectDto);
    void deleteSubject(Long subjectId);
}
