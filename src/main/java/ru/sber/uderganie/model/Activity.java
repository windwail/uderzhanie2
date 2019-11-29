package ru.sber.uderganie.model;

import lombok.*;

import javax.persistence.*;

@Data

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "ACTIVITY")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_activity_id")
    @SequenceGenerator(name = "seq_activity_id", sequenceName = "seq_activity_id", allocationSize = 1)
    private Long id;

    public Activity(String name) {
        this.name = name;
    }

    private String name;
}
