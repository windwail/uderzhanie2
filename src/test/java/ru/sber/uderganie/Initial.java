package ru.sber.uderganie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sber.uderganie.model.Effectiveness;
import ru.sber.uderganie.model.KeySkill;
import ru.sber.uderganie.repository.*;

import java.lang.reflect.Array;
import java.util.Arrays;
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

    @Test
    public void createEmployee() {

        List<KeySkill> skills = Arrays.asList("Java", "Java Script", "Oracle", "React")
                .stream().map(KeySkill::new)
                //.map(keySkillRepository::save)
                .collect(Collectors.toList());

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

    }
}
