package ru.sber.uderganie.model;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "PROCESS_DEFINITION")
public class ProcessDefinition {

    public enum ProcessType {
        STAY_INTERVIEW(0, "StayInterview");

        private final Integer ID;
        private final String name;

        ProcessType(Integer anID, String aName) {
            this.ID = anID;
            this.name = aName;
        }

        public static ProcessType Role(Long anID) {
            switch (anID.intValue()) {
                default: return STAY_INTERVIEW;
            }
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_process_definition_id")
    @SequenceGenerator(name = "seq_process_definition_id", sequenceName = "seq_process_definition_id", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProcessType type;
}
