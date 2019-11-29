package ru.sber.uderganie.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "TASK_HISTORY")
public class TaskHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_task_history_id")
    @SequenceGenerator(name = "seq_task_history_id", sequenceName = "seq_task_history_id", allocationSize = 1)
    private Long id;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "from_task_id", nullable = false)
    private Task fromTask;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "to_task_id", nullable = false)
    private Task toTask;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cause_rule_id", nullable = false)
    private Rule causeRule;
}
