package net.javaguides.sms.entity;

import jakarta.persistence.*;
import lombok.*;
import net.javaguides.sms.commons.BaseEntity;


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

}
