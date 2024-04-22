package com.springmvc.sms.controller;

import com.springmvc.sms.dto.LessonDto;
import com.springmvc.sms.dto.SubjectDto;
import com.springmvc.sms.dto.TeacherDto;
import com.springmvc.sms.service.impl.LessonService;
import com.springmvc.sms.service.impl.SubjectService;
import com.springmvc.sms.service.impl.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;

    @GetMapping({"/lessons"})
    public String listLessons(Model model){
        List<LessonDto> lessons = lessonService.getAllLessons();
        List<TeacherDto> teachers = teacherService.getAllTeachers();
        List<SubjectDto> subjects = subjectService.getAllSubjects();

        model.addAttribute("lessons", lessons);
        model.addAttribute("teachers", teachers);
        model.addAttribute("subjects", subjects);

        return "lessons";
    }

    @GetMapping("/lessons/new")
    public String newLesson(Model model){
        List<TeacherDto> teachers = teacherService.getAllTeachers();
        List<SubjectDto> subjects = subjectService.getAllSubjects();

        LessonDto lessonDto = new LessonDto();

        model.addAttribute("lesson", lessonDto);
        model.addAttribute("teachers", teachers);
        model.addAttribute("subjects", subjects);

        return "create_lesson";
    }

    @PostMapping("/lessons")
    public String saveLesson(@Valid @ModelAttribute("lesson") LessonDto lesson,
                                 BindingResult result,
                                 Model model){
        if(result.hasErrors()){
            model.addAttribute("lesson", lesson);
            return "create_lesson";
        }

        lessonService.createLesson(lesson);
        return "redirect:/lessons";
    }

    @GetMapping("/lessons/edit/{lessonId}")
    public String editLesson(@PathVariable("lessonId") Long lessonId,
                                 Model model){
        LessonDto lesson = lessonService.getLessonById(lessonId);
        List<TeacherDto> teachers = teacherService.getAllTeachers();
        List<SubjectDto> subjects = subjectService.getAllSubjects();

        model.addAttribute("lesson", lesson);
        model.addAttribute("teachers", teachers);
        model.addAttribute("subjects", subjects);

        return "edit_lesson";
    }

    @PostMapping("/lessons/{lessonId}")
    public String updateLesson(@PathVariable("lessonId") Long lessonId,
                                   @Valid @ModelAttribute("lesson") LessonDto lessonDto,
                                   BindingResult result,
                                   Model model){
        if(result.hasErrors()){
            model.addAttribute("lesson", lessonDto);
            return "edit_lesson";
        }
        lessonDto.setId(lessonId);
        lessonService.updateLesson(lessonDto);
        return "redirect:/lessons";
    }

    @GetMapping("/lessons/delete/{lessonId}")
    public String deleteLesson(@PathVariable("lessonId") Long lessonId){
        lessonService.deleteLesson(lessonId);
        return "redirect:/lessons";
    }

}
