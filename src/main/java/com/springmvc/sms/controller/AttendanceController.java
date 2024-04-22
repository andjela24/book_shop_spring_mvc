package com.springmvc.sms.controller;

import com.springmvc.sms.dto.AttendanceDto;
import com.springmvc.sms.dto.LessonDto;
import com.springmvc.sms.dto.StudentDto;
import com.springmvc.sms.service.impl.AttendanceService;
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
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final LessonService lessonService;
    private final StudentService studentService;

    @GetMapping({"/attendances"})
    public String listAttendances(Model model) {
        List<AttendanceDto> attendances = attendanceService.getAllAttendances();
        List<StudentDto> students = studentService.getAllStudents();
        List<LessonDto> lessons = lessonService.getAllLessons();

        model.addAttribute("attendances", attendances);
        model.addAttribute("students", students);
        model.addAttribute("lessons", lessons);

        return "attendances";
    }

    @GetMapping("/attendances/new")
    public String newAttendance(Model model) {
        List<StudentDto> students = studentService.getAllStudents();
        List<LessonDto> lessons = lessonService.getAllLessons();

        AttendanceDto attendanceDto = new AttendanceDto();
        attendanceDto.setPresent(true);

        model.addAttribute("attendance", attendanceDto);
        model.addAttribute("students", students);
        model.addAttribute("lessons", lessons);

        return "create_attendance";
    }

    @PostMapping("/attendances")
    public String saveAttendance(@Valid @ModelAttribute("attendance") AttendanceDto attendance,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("attendance", attendance);
            return "create_attendance";
        }

        attendanceService.createAttendance(attendance);
        return "redirect:/attendances";
    }

    @GetMapping("/attendances/edit/{attendanceId}")
    public String editAttendance(@PathVariable("attendanceId") Long attendanceId,
                                 Model model) {
        AttendanceDto attendance = attendanceService.getAttendanceById(attendanceId);
        model.addAttribute("attendance", attendance);
        return "edit_attendance";
    }

    @PostMapping("/attendances/{attendanceId}")
    public String updateAttendance(@PathVariable("attendanceId") Long attendanceId,
                                   @Valid @ModelAttribute("attendance") AttendanceDto attendanceDto,
                                   BindingResult result,
                                   Model model) {
        if (result.hasErrors()) {
            model.addAttribute("attendance", attendanceDto);
            return "edit_attendance";
        }
        attendanceService.updateAttendance(attendanceId, attendanceDto);
        return "redirect:/attendances";
    }

    @GetMapping("/attendances/delete/{attendanceId}")
    public String deleteAttendance(@PathVariable("attendanceId") Long attendanceId) {
        attendanceService.deleteAttendance(attendanceId);
        return "redirect:/attendances";
    }

    @GetMapping("/attendances/{attendanceId}/view")
    public String viewAttendance(@PathVariable("attendanceId") Long attendanceId,
                                 Model model) {
        AttendanceDto attendanceDto = attendanceService.getAttendanceById(attendanceId);
        model.addAttribute("attendance", attendanceDto);
        return "view_attendance";
    }
}
