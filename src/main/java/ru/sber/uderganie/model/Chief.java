package ru.sber.uderganie.model;

import lombok.*;

import javax.persistence.*;

@Data

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "CHIEF")
public class Chief {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_chief_id")
    @SequenceGenerator(name = "seq_chief_id", sequenceName = "seq_chief_id", allocationSize = 1)
    private Long id;

    // Табельный номер сотрудинка
    String employeeId;

    String firstName;

    String middleName;

    String lastName;

    // Подразделение
    String department;
}
