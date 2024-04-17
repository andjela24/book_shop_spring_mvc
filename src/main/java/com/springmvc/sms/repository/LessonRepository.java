package com.springmvc.sms.repository;

import com.springmvc.sms.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
