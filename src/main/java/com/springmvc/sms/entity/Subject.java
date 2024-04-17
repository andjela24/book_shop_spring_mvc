package com.springmvc.sms.entity;

import jakarta.persistence.*;
import lombok.*;
import com.springmvc.sms.commons.BaseEntity;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject")
public class Subject extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Subject(String name) {
        this.name = name;
    }
}
