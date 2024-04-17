package com.springmvc.sms.service.impl;

import com.springmvc.sms.dto.AttendanceDto;
import com.springmvc.sms.dto.LessonDto;
import com.springmvc.sms.entity.Attendance;
import com.springmvc.sms.entity.Lesson;
import com.springmvc.sms.excetion.DataNotValidException;
import com.springmvc.sms.excetion.ResourceNotFoundException;
import com.springmvc.sms.mapper.AttendanceMapper;
import com.springmvc.sms.mapper.LessonMapper;
import com.springmvc.sms.repository.LessonRepository;
import com.springmvc.sms.service.ILessonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService {

    private final LessonRepository lessonRepository;

    @Override
    public LessonDto createLesson(LessonDto lessonDto) {
        if (lessonDto.getTeacher() == null) {
            throw new DataNotValidException("Teacher must exists");
        }
        if (lessonDto.getSubject() == null) {
            throw new DataNotValidException("Subject must exists");
        }
        Lesson lesson = Lesson.builder()
                .date(lessonDto.getDate())
                .teacher(lessonDto.getTeacher())
                .subject(lessonDto.getSubject())
                .build();

        lessonRepository.save(lesson);

        LessonDto createdLessonDto = LessonMapper.mapToLessonDto(lesson);
        return createdLessonDto;
    }

    @Override
    public List<LessonDto> getAllLessons() {
        List<LessonDto> foundLessons = new ArrayList<>();
        List<Lesson> lessons = lessonRepository.findAll();

        if (lessons.isEmpty()) {
            throw new ResourceNotFoundException("List of lessons is empty");
        }

        for (Lesson lesson : lessons) {
            LessonDto lessonDto = LessonMapper.mapToLessonDto(lesson);
            foundLessons.add(lessonDto);
        }

        return foundLessons;
    }

    @Override
    public LessonDto getLessonById(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new ResourceNotFoundException("Didn't find lesson with id:" + lessonId));
        LessonDto lessonDto = LessonMapper.mapToLessonDto(lesson);
        return lessonDto;
    }

    @Override
    public LessonDto updateLesson(Long lessonId, LessonDto lessonDto) {
        Lesson foundLesson = lessonRepository.findById(lessonId).orElseThrow(() -> new ResourceNotFoundException("Entity whit id " + lessonId + " could not be updated"));

        boolean isChanged = false;

        if (lessonDto.getTeacher() == null) {
            throw new DataNotValidException("Teacher must exists");
        } else {
            isChanged = true;
            foundLesson.setTeacher(lessonDto.getTeacher());
        }
        if (lessonDto.getSubject() == null) {
            throw new DataNotValidException("Subject must exists");
        } else {
            isChanged = true;
            foundLesson.setSubject(lessonDto.getSubject());
        }

        if (isChanged) {
            foundLesson.setUpdatedAt(LocalDateTime.now());
            lessonRepository.save(foundLesson);
        }
        LessonDto updatedLessonDto = LessonMapper.mapToLessonDto(foundLesson);

        return updatedLessonDto;
    }

    @Override
    public void deleteLesson(Long lessonId) {
        if (!lessonRepository.existsById(lessonId)) {
            throw new ResourceNotFoundException("Didn't find lesson with id:" + lessonId);
        }
        lessonRepository.deleteById(lessonId);
    }
}
