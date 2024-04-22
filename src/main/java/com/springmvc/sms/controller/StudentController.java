package com.springmvc.sms.controller;

import com.springmvc.sms.dto.LessonDto;
import com.springmvc.sms.dto.StudentDto;
import com.springmvc.sms.service.impl.LessonService;
import com.springmvc.sms.service.impl.StudentService;
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
//    private final LessonService lessonService;

//    @GetMapping("/create-attendance/{studentId}")
//    public String getCreateAttendancePage(@PathVariable("studentId") Long studentId, Model model) {
//        StudentDto studentDto = studentService.getStudentById(studentId);
//        System.out.println("Student ID: " + studentDto);
//        String studentName = studentDto.getFirstName() + " " + studentDto.getLastName();
//        List<LessonDto> lessons = lessonService.getAllLessons();
//        model.addAttribute("lessons", lessons);
//        model.addAttribute("studentName", studentName);
//        model.addAttribute("studentId", studentId);
//        model.addAttribute("student", studentDto);
//        return "create_attendance";
//    }

    @GetMapping({"/students"})
    public String listStudents(Model model) {
        List<StudentDto> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/students/new")
    public String newStudent(Model model) {
        StudentDto studentDto = new StudentDto();
        model.addAttribute("student", studentDto);
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
        model.addAttribute("student", student);
        return "edit_student";
    }

    @PostMapping("/students/{studentId}")
    public String updateStudent(@PathVariable("studentId") Long studentId,
                                @Valid @ModelAttribute("student") StudentDto studentDto,
                                BindingResult result,
                                Model model) {
        studentDto.setId(studentId);
        System.out.println("Student DTO: " + studentDto.getId());
        if (result.hasErrors()) {
            model.addAttribute("student", studentDto);
            return "edit_student";
//            return "Ovde puca";
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

    @GetMapping("/students/{studentId}/view")
    public String viewStudent(@PathVariable("studentId") Long studentId,
                              Model model) {
        StudentDto studentDto = studentService.getStudentById(studentId);
        model.addAttribute("student", studentDto);
        return "view_student";
    }
}
