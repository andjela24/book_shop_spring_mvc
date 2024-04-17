package com.springmvc.sms.service;

import com.springmvc.sms.dto.LessonDto;
import com.springmvc.sms.entity.Lesson;

import java.util.List;

public interface ILessonService {
    LessonDto createLesson(LessonDto lessonDto);
    List<LessonDto> getAllLessons();
    LessonDto getLessonById(Long lessonId);
    LessonDto updateLesson(Long lessonId,LessonDto lessonDto);
    void deleteLesson(Long lessonId);
}
