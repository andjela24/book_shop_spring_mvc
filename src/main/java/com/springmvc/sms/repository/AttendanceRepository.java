package com.springmvc.sms.repository;

import com.springmvc.sms.entity.Attendance;
import com.springmvc.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
