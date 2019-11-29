package ru.sber.uderganie.model;


import lombok.*;

import javax.persistence.*;

@Data

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "SALARY_STORY")
public class SalaryStory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_salary_story_id")
    @SequenceGenerator(name = "seq_salary_story_id", sequenceName = "seq_salary_story_id", allocationSize = 1)
    private Long id;

    public SalaryStory(String name) {
        this.name = name;
    }

    private String name;
}
