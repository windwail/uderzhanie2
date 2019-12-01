package ru.sber.uderganie.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "CLAIM")
public class Claim {

    public enum ClaimStatus {
        NEW,
        PROCESSED,
        ERROR
    }

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
    @Enumerated(EnumType.STRING)
    ClaimStatus claimStatus;

    // Время последней смены статуса. Время обработки.
    LocalDateTime processedDate;

    // Ошибка при обработке.
    String processedError;

    // Статус исполнения
    String executionStatus;

    // Причина указанная сотрудником
    String employeeReason;

    Long interval;


}
