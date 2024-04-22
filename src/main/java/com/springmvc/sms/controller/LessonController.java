package com.springmvc.sms.controller;

import com.springmvc.sms.dto.LessonDto;
import com.springmvc.sms.service.impl.LessonService;
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

    // handler method to handle list lessons request
    @GetMapping({"/lessons"})
    public String listLessons(Model model){
        List<LessonDto> lessons = lessonService.getAllLessons();
        model.addAttribute("lessons", lessons);
        return "lessons";
    }

    // handler method to handle new lesson request
    @GetMapping("/lessons/new")
    public String newLesson(Model model){
        // lesson model object to store lesson form data
        LessonDto lessonDto = new LessonDto();
        model.addAttribute("lesson", lessonDto);
        return "create_lesson";
    }

    // handler method to handle save lesson form submit request
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

    // handler method to handle edit lesson request
    @GetMapping("/lessons/edit/{lessonId}")
    public String editLesson(@PathVariable("lessonId") Long lessonId,
                                 Model model){
        LessonDto lesson = lessonService.getLessonById(lessonId);
        model.addAttribute("lesson", lesson);
        return "edit_lesson";
    }

    // handler method to handle edit lesson form submit request
    @PostMapping("/lessons/{lessonId}")
    public String updateLesson(@PathVariable("lessonId") Long lessonId,
                                   @Valid @ModelAttribute("lesson") LessonDto lessonDto,
                                   BindingResult result,
                                   Model model){
        if(result.hasErrors()){
            model.addAttribute("lesson", lessonDto);
            return "edit_lesson";
        }
//        lessonDto.setId(lessonId);
        lessonService.updateLesson(lessonId, lessonDto);
        return "redirect:/lessons";
    }

    // Handler method to handle delete lesson request
    @GetMapping("/lessons/delete/{lessonId}")
    public String deleteLesson(@PathVariable("lessonId") Long lessonId){
        lessonService.deleteLesson(lessonId);
        return "redirect:/lessons";
    }

    // Handler method to handle view lesson request
    @GetMapping("/lessons/{lessonId}/view")
    public String viewLesson(@PathVariable("lessonId") Long lessonId,
                                 Model model){
        LessonDto lessonDto = lessonService.getLessonById(lessonId);
        model.addAttribute("lesson", lessonDto);
        return "view_lesson";
    }
}
