package com.springmvc.sms.repository;

import com.springmvc.sms.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
