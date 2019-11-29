package ru.sber.uderganie.model;


import lombok.*;

import javax.persistence.*;

@Data

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "SCORE_CLIENT_BASE")
public class ScoreClientBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_score_client_base_id")
    @SequenceGenerator(name = "seq_score_client_base_id", sequenceName = "seq_score_client_base_id", allocationSize = 1)
    private Long id;

    public ScoreClientBase(String name) {
        this.name = name;
    }

    private String name;
}
