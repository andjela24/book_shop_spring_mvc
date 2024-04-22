package com.springmvc.sms.controller;

import com.springmvc.sms.dto.TeacherDto;
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
public class TeacherController {
    private final TeacherService teacherService;

    // handler method to handle list teachers request
    @GetMapping({"/teachers"})
    public String listTeachers(Model model){
        List<TeacherDto> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teachers";
    }

    // handler method to handle new teacher request
    @GetMapping("/teachers/new")
    public String newTeacher(Model model){
        // teacher model object to store teacher form data
        TeacherDto teacherDto = new TeacherDto();
        model.addAttribute("teacher", teacherDto);
        return "create_teacher";
    }

    // handler method to handle save teacher form submit request
    @PostMapping("/teachers")
    public String saveTeacher(@Valid @ModelAttribute("teacher") TeacherDto teacher,
                                 BindingResult result,
                                 Model model){
        if(result.hasErrors()){
            model.addAttribute("teacher", teacher);
            return "create_teacher";
        }

        teacherService.createTeacher(teacher);
        return "redirect:/teachers";
    }

    // handler method to handle edit teacher request
    @GetMapping("/teachers/edit/{teacherId}")
    public String editTeacher(@PathVariable("teacherId") Long teacherId,
                                 Model model){
        TeacherDto teacher = teacherService.getTeacherById(teacherId);
        model.addAttribute("teacher", teacher);
        return "edit_teacher";
    }

    // handler method to handle edit teacher form submit request
    @PostMapping("/teachers/{teacherId}")
    public String updateTeacher(@PathVariable("teacherId") Long teacherId,
                                   @Valid @ModelAttribute("teacher") TeacherDto teacherDto,
                                   BindingResult result,
                                   Model model){
        if(result.hasErrors()){
            model.addAttribute("teacher", teacherDto);
            return "edit_teacher";
        }
//        teacherDto.setId(teacherId);
        teacherService.updateTeacher(teacherId, teacherDto);
        return "redirect:/teachers";
    }

    // Handler method to handle delete teacher request
    @GetMapping("/teachers/delete/{teacherId}")
    public String deleteTeacher(@PathVariable("teacherId") Long teacherId){
        teacherService.deleteTeacher(teacherId);
        return "redirect:/teachers";
    }

    // Handler method to handle view teacher request
    @GetMapping("/teachers/{teacherId}/view")
    public String viewTeacher(@PathVariable("teacherId") Long teacherId,
                                 Model model){
        TeacherDto teacherDto = teacherService.getTeacherById(teacherId);
        model.addAttribute("teacher", teacherDto);
        return "view_teacher";
    }
}
