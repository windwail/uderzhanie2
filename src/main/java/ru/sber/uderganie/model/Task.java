package ru.sber.uderganie.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "TASK")
public class Task {

    public enum TaskType {
        CHIEF,
        CHIEFPLUS,
        BPHR
    }

    public enum TaskStatus {
        SCHEDULED,
        OUTDATED,
        DONE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_task_id")
    @SequenceGenerator(name = "seq_task_id", sequenceName = "seq_task_id", allocationSize = 1)
    private Long id;

    private String chiefId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_result_id")
    private InterviewResult result;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "process_instance_id", referencedColumnName = "id", nullable = false)
    private ProcessInstance processInstance;

    @Enumerated(EnumType.STRING)
    private TaskType type;

    Boolean active;

    LocalDateTime doUntil;

    @Enumerated(EnumType.STRING)
    TaskStatus status;

}
