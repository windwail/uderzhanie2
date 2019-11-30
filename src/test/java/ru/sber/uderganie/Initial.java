package ru.sber.uderganie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sber.uderganie.model.*;
import ru.sber.uderganie.repository.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Initial {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProcessDefinitionRepository processDefinitionRepository;

    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    ProcessInstanceRepository processInstanceRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    KeySkillRepository keySkillRepository;

    @Autowired
    EffectivenessRepository effectivenessRepository;

    @Autowired
    SalaryStoryRepository salaryStoryRepository;

    @Test
    public void createEmployee() {

        List<KeySkill> skills = Arrays.asList("Java", "Java Script", "Oracle", "React")
                .stream().map(KeySkill::new)
                //.map(keySkillRepository::save)
                .collect(Collectors.toList());

        Employee e = Employee.builder()
                .cause("cause")
                .grade("grade")
                .firingDate(LocalDate.now())
                .firstName("firstName")
                .claimCounts("1")
                .daysUntilFiring("1")
                .department("department")
                .individualSchedule("false")
                .lastName("lastName")
                .middleName("middleName")
                .positionId("1")
                .positionName("positionName")
                .score5plus("1")
                .vacationDays("1")
                .employeeId("1")
                .city("Moscow")
                .keySkills(new HashSet<>())
                .salaryStories(new HashSet<>())
                .scoreClientBase(new HashSet<>())
                .effectiveness(new HashSet<>())
                .build();

        Effectiveness e1 = Effectiveness.builder()
                .year(2019)
                .quarter(1)
                .km("36/24")
                .abcd("24/81")
                .restiveness("B")
                .value("C")
                .interaction("4.1")
                .it("3.9")
                .systemThinking("4.5")
                .responsibility("1")
                .digitalSkills("3.7")
                .teamDevelopment("4.5")
                .customerCentricity("4.0")
                .build();


        Effectiveness e2 = Effectiveness.builder()
                .year(2019)
                .quarter(2)
                .km("36/24")
                .abcd("24/81")
                .restiveness("B")
                .value("C")
                .interaction("4.1")
                .it("3.9")
                .systemThinking("4.5")
                .responsibility("1")
                .digitalSkills("3.7")
                .teamDevelopment("4.5")
                .customerCentricity("4.0")
                .build();


        Effectiveness e3 = Effectiveness.builder()
                .year(2019)
                .quarter(3)
                .km("36/24")
                .abcd("24/81")
                .restiveness("B")
                .value("C")
                .interaction("4.1")
                .it("3.9")
                .systemThinking("4.5")
                .responsibility("1")
                .digitalSkills("3.7")
                .teamDevelopment("4.5")
                .customerCentricity("4.0")
                .build();


        Effectiveness e4 = Effectiveness.builder()
                .year(2019)
                .quarter(4)
                .km("36/24")
                .abcd("24/81")
                .restiveness("B")
                .value("C")
                .interaction("4.1")
                .it("3.9")
                .systemThinking("4.5")
                .responsibility("1")
                .digitalSkills("3.7")
                .teamDevelopment("4.5")
                .customerCentricity("4.0")
                .build();


        SalaryStory ss1 = SalaryStory.builder()
                .fromDate(LocalDate.of(2019,1,1))
                .toDate(LocalDate.of(2019,3,1))
                .salary("265 000р")
                .salaryBound("180т.р.-290т.р")
                .budget("1 560 940р.")
                .salaryChangePercent("+15%")
                .salaryChange("230 000 -> 265 000")
                .positionChange("false")
                .build();

        SalaryStory ss2 = SalaryStory.builder()
                .fromDate(LocalDate.of(2019,3,1))
                .toDate(LocalDate.of(2019,6,1))
                .salary("265 000р")
                .salaryBound("180т.р.-290т.р")
                .budget("1 560 940р.")
                .salaryChangePercent("+15%")
                .salaryChange("230 000 -> 265 000")
                .positionChange("false")
                .build();

        SalaryStory ss3 = SalaryStory.builder()
                .fromDate(LocalDate.of(2019,6,1))
                .toDate(null)
                .salary("265 000р")
                .salaryBound("180т.р.-290т.р")
                .budget("1 560 940р.")
                .salaryChangePercent("+15%")
                .salaryChange("230 000 -> 265 000")
                .positionChange("false")
                .build();



        e.getSalaryStories().add(ss1);
        e.getSalaryStories().add(ss2);
        e.getSalaryStories().add(ss3);

        e.getEffectiveness().add(e1);
        e.getEffectiveness().add(e2);
        e.getEffectiveness().add(e3);
        e.getEffectiveness().add(e4);

        e.getKeySkills().addAll(skills);

        employeeRepository.save(e);

        Claim claim = new Claim();
        claim.setClaimStatus(Claim.ClaimStatus.NEW);
        claim.setEmployeeId(e.getEmployeeId());
        claim.setEmployeeReason("По собственному желанию");
        claim.setFireDate(LocalDate.of(2019, 12,12));
        claim.setExecutionStatus("В работе");
        claim.setFireType("Не устроил оклад");

        claimRepository.save(claim);


    }
}
