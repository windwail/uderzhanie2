package ru.sber.uderganie.model;

import lombok.*;

import javax.persistence.*;

@Data

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "STATUS")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_status_id")
    @SequenceGenerator(name = "seq_status_id", sequenceName = "seq_status_id", allocationSize = 1)
    private Long id;

}
