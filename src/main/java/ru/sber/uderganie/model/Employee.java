package ru.sber.uderganie.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_employee_id")
    @SequenceGenerator(name = "seq_employee_id", sequenceName = "seq_employee_id", allocationSize = 1)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_key_skill",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "key_skill_id")
    )
    private Set<KeySkill> keySkills = new HashSet<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_score_client_base",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "score_client_base_id")
    )
    private Set<ScoreClientBase> scoreClientBase = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_effectiveness",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "effectiveness_id")
    )
    private Set<Effectiveness> effectiveness = new HashSet<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_salary_story",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "salary_story_id")
    )
    private Set<SalaryStory> salaryStories = new HashSet<>();

    // Табельный номер сотрудинка
    String employeeId;

    String firstName;

    String middleName;

    String lastName;

    // Дата предстоящего увольнения
    LocalDate firingDate;

    // ID позиции
    String positionId;

    // Доложность
    String positionName;

    // Подразделение
    String department;

    // Грейд
    String grade;

    String city;

    // Дней до увольнения
    String daysUntilFiring;

    // Причина увольнения указанная сотрудинком
    String cause;

    // Оценка 5+
    String score5plus;

    // количество заявлений на увольнение
    String claimCounts;

    // дней отпуска;
    String vacationDays;

    String individualSchedule;
}


