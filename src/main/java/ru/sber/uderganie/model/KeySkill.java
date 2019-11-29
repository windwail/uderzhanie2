package ru.sber.uderganie.model;

import lombok.*;

import javax.persistence.*;

@Data

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "KEY_SKILL")
public class KeySkill {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_key_skill_id")
    @SequenceGenerator(name = "seq_key_skill_id", sequenceName = "seq_key_skill_id", allocationSize = 1)
    private Long id;

    public KeySkill(String name) {
        this.name = name;
    }

    private String name;
}
