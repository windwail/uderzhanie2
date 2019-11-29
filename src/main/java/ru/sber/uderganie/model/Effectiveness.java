package ru.sber.uderganie.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "EFFECTIVENESS")
public class Effectiveness {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_effectiveness_id")
    @SequenceGenerator(name = "seq_effectiveness_id", sequenceName = "seq_effectiveness_id", allocationSize = 1)
    private Long id;

    private Integer year;

    private Integer quarter;

    // Производительность (КМ/Среднее): 36/24
    private String km;

    // Клиентская база (AB/CD): 24/81
    private String abcd;

    // 5+ Результативность: B
    private String restiveness;

    //	5+ Ценность: С
    private String value;

    //  Организация взаимодействия: 4.1
    private String interaction;

    // IT: 3.9
    private String it;

    // Системное мышление и решение проблем: 4.5
    private String systemThinking;

    // Упрелвение результатом и ответственность: 3.4
    private String responsibility;

    // Управление собой: 1
    private String selfConfidence;

    // Инновационность и Digital-навыки: 3.7
    private String digitalSkills;

    // Развитие команд и сотрудничество: 4.5
    private String teamDevelopment;

    // Клиентоцентричность: 4.0
    private String customerCentricity;

}
