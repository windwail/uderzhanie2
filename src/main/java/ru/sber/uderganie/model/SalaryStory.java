package ru.sber.uderganie.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "SALARY_STORY")
public class SalaryStory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_salary_story_id")
    @SequenceGenerator(name = "seq_salary_story_id", sequenceName = "seq_salary_story_id", allocationSize = 1)
    private Long id;

    LocalDate fromDate;
    LocalDate toDate;
    String salary;

    // 180т.р.-290т.р
    String salaryBound;

    // 1 560 940р.
    String budget;

    // +15%
    String salaryChangePercent;

    // 230 000 -> 265 000
    String salaryChange;

    // true
    String positionChange;
}
