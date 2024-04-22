package com.springmvc.sms.controller;

import com.springmvc.sms.dto.StudentDto;
import com.springmvc.sms.dto.SubjectDto;
import com.springmvc.sms.service.impl.StudentService;
import com.springmvc.sms.service.impl.SubjectService;
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
public class SubjectController {
    private final SubjectService subjectService;
    private final StudentService studentService;

    @GetMapping({"/subjects"})
    public String listSubjects(Model model){
        List<SubjectDto> subjects = subjectService.getAllSubjects();
        List<StudentDto> students = studentService.getAllStudents();

        model.addAttribute("subjects", subjects);
        model.addAttribute("students", students);

        return "subjects";
    }

    // handler method to handle new subject request
    @GetMapping("/subjects/new")
    public String newSubject(Model model){
        // subject model object to store subject form data
        SubjectDto subjectDto = new SubjectDto();
        model.addAttribute("subject", subjectDto);
        return "create_subject";
    }

    // handler method to handle save subject form submit request
    @PostMapping("/subjects")
    public String saveSubject(@Valid @ModelAttribute("subject") SubjectDto subject,
                                 BindingResult result,
                                 Model model){
        if(result.hasErrors()){
            model.addAttribute("subject", subject);
            return "create_subject";
        }

        subjectService.createSubject(subject);
        return "redirect:/subjects";
    }

    // handler method to handle edit subject request
    @GetMapping("/subjects/edit/{subjectId}")
    public String editSubject(@PathVariable("subjectId") Long subjectId,
                                 Model model){
        SubjectDto subject = subjectService.getSubjectById(subjectId);
        model.addAttribute("subject", subject);
        return "edit_subject";
    }

    // handler method to handle edit subject form submit request
    @PostMapping("/subjects/{subjectId}")
    public String updateSubject(@PathVariable("subjectId") Long subjectId,
                                   @Valid @ModelAttribute("subject") SubjectDto subjectDto,
                                   BindingResult result,
                                   Model model){
        if(result.hasErrors()){
            model.addAttribute("subject", subjectDto);
            return "edit_subject";
        }
//        subjectDto.setId(subjectId);
        subjectService.updateSubject(subjectId, subjectDto);
        return "redirect:/subjects";
    }

    // Handler method to handle delete subject request
    @GetMapping("/subjects/delete/{subjectId}")
    public String deleteSubject(@PathVariable("subjectId") Long subjectId){
        subjectService.deleteSubject(subjectId);
        return "redirect:/subjects";
    }

    // Handler method to handle view subject request
    @GetMapping("/subjects/{subjectId}/view")
    public String viewSubject(@PathVariable("subjectId") Long subjectId,
                                 Model model){
        SubjectDto subjectDto = subjectService.getSubjectById(subjectId);
        model.addAttribute("subject", subjectDto);
        return "view_subject";
    }
}
