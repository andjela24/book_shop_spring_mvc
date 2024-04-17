package com.springmvc.sms.repository;

import com.springmvc.sms.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
