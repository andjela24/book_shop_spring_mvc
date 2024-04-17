package com.springmvc.sms.mapper;

import com.springmvc.sms.dto.LessonDto;
import com.springmvc.sms.entity.Lesson;

public class LessonMapper {
    public static LessonDto mapToLessonDto(Lesson lesson) {
        LessonDto lessonDto = new LessonDto(
                lesson.getDate(),
                lesson.getTeacher(),
                lesson.getSubject()
        );
        return lessonDto;
    }
    public static Lesson mapToLesson(LessonDto lessonDto) {
        Lesson lesson = new Lesson(
                lessonDto.getDate(),
                lessonDto.getTeacher(),
                lessonDto.getSubject()
        );
        return lesson;
    }
}
