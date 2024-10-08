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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final SubjectService subjectService;

    @GetMapping({"/students"})
    public String listStudents(Model model) {
        List<StudentDto> students = studentService.getAllStudents();
        List<SubjectDto> subjects = subjectService.getAllSubjects();

        model.addAttribute("students", students);
        model.addAttribute("subjects", subjects);

        return "students";
    }

    @GetMapping("/students/new")
    public String newStudent(Model model) {
        StudentDto studentDto = new StudentDto();
        List<SubjectDto> subjectsList = subjectService.getAllSubjects();

        model.addAttribute("student", studentDto);
        model.addAttribute("subjectsList", subjectsList);

        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto student,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student", student);
            return "create_student";
        }

        studentService.createStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{studentId}")
    public String editStudent(@PathVariable("studentId") Long studentId,
                              Model model) {
        StudentDto student = studentService.getStudentById(studentId);
        List<SubjectDto> subjectsList = subjectService.getAllSubjects();

        model.addAttribute("student", student);
        model.addAttribute("subjectsList", subjectsList);

        return "edit_student";
    }

    @PostMapping("/students/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId,
                                @Valid @ModelAttribute("student") StudentDto studentDto,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("student", studentDto);
            return "edit_student";
        }
        studentDto.setId(studentId);
        studentService.updateStudent(studentDto);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{studentId}")
    public String deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }

}
