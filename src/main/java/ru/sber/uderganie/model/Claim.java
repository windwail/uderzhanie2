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
@Table(name = "CLAIM")
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_claim_id")
    @SequenceGenerator(name = "seq_claim_id", sequenceName = "seq_claim_id", allocationSize = 1)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "process_instance_id")
    private ProcessInstance processInstance;

    // Табельный номер сотрудинка
    String employeeId;

    // ID заявления
    String claimId;

    // Дата увольнения
    LocalDate fireDate;

    // Тип увольнения, например, по собственному желанию
    String fireType;

    // Статус заявления
    String claimStatus;

    // Статус исполнения
    String executionStatus;

    // Причина указанная сотрудником
    String employeeReason;

}
