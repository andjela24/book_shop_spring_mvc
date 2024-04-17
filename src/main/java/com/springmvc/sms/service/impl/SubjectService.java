package com.springmvc.sms.service.impl;

import com.springmvc.sms.dto.SubjectDto;
import com.springmvc.sms.entity.Subject;
import com.springmvc.sms.excetion.DataNotValidException;
import com.springmvc.sms.excetion.ResourceNotFoundException;
import com.springmvc.sms.mapper.SubjectMapper;
import com.springmvc.sms.repository.SubjectRepository;
import com.springmvc.sms.service.ISubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService implements ISubjectService {
    
    private final SubjectRepository subjectRepository;

    @Override
    public SubjectDto createSubject(SubjectDto subjectDto) {
        if (subjectDto.getName().isEmpty()) {
            throw new DataNotValidException("Subject name can not be empty");
        }

        Subject subject = Subject.builder()
                .name(subjectDto.getName())
                .build();

        subjectRepository.save(subject);

        SubjectDto createdSubjectDto = SubjectMapper.mapToSubjectDto(subject);
        return createdSubjectDto;
    }

    @Override
    public List<SubjectDto> getAllSubjects() {
        List<SubjectDto> foundSubjects = new ArrayList<>();
        List<Subject> subjects = subjectRepository.findAll();

        if (subjects.isEmpty()) {
            throw new ResourceNotFoundException("List of subjects is empty");
        }

        for (Subject subject : subjects) {
            SubjectDto subjectDto = SubjectMapper.mapToSubjectDto(subject);
            foundSubjects.add(subjectDto);
        }

        return foundSubjects;
    }

    @Override
    public SubjectDto getSubjectById(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Didn't find subject with id:" + subjectId));
        SubjectDto subjectDto = SubjectMapper.mapToSubjectDto(subject);
        return subjectDto;
    }

    @Override
    public SubjectDto updateSubject(Long subjectId, SubjectDto subjectDto) {
        Subject foundSubject = subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Entity whit id " + subjectId + " could not be updated"));

        boolean isChanged = false;

        if (subjectDto.getName().isEmpty()) {
            throw new DataNotValidException("Subject name can not be empty");
        } else {
            isChanged = true;
            foundSubject.setName(subjectDto.getName());
        }

        if (isChanged) {
            foundSubject.setUpdatedAt(LocalDateTime.now());
            subjectRepository.save(foundSubject);
        }
        SubjectDto updatedSubjectDto = SubjectMapper.mapToSubjectDto(foundSubject);

        return updatedSubjectDto;    }

    @Override
    public void deleteSubject(Long subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            throw new ResourceNotFoundException("Didn't find subject with id:" + subjectId);
        }
        subjectRepository.deleteById(subjectId);
    }
}
