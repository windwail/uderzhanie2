package ru.sber.uderganie.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "PROCESS_INSTANCE")
public class ProcessInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_process_instance_id")
    @SequenceGenerator(name = "seq_process_instance_id", sequenceName = "seq_process_instance_id", allocationSize = 1)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "process_definition_id")
    private ProcessDefinition definition;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_id")
    private Claim claim;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "processInstance", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Task> tasks = new HashSet<>();

    Boolean active;

}
