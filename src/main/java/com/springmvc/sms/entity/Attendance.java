package com.springmvc.sms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.springmvc.sms.commons.BaseEntity;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attendance")
public class Attendance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    private boolean isPresent;

    public Attendance(Student student, Lesson lesson, boolean isPresent) {
        this.student = student;
        this.lesson = lesson;
        this.isPresent = isPresent;
    }
}
