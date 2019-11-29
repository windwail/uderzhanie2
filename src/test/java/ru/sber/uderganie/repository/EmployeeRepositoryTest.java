package ru.sber.uderganie.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sber.uderganie.model.*;

import java.time.LocalDate;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TestEntityManager entityManager;

    KeySkill ks1;
    KeySkill ks2;
    KeySkill ks3;

    SalaryStory ss1;
    SalaryStory ss2;
    SalaryStory ss3;

    Effectiveness ef1;
    Effectiveness ef2;
    Effectiveness ef3;

    ScoreClientBase scb1;
    ScoreClientBase scb2;
    ScoreClientBase scb3;

    Employee e;

    @Before
    public void init() throws Exception {

        ks1 = entityManager.persistAndFlush(new KeySkill("KeySkill1"));
        ks2 = entityManager.persistAndFlush(new KeySkill("KeySkill2"));
        ks3 = entityManager.persistAndFlush(new KeySkill("KeySkill3"));

        ss1 = entityManager.persistAndFlush(new SalaryStory("SalaryStory1"));
        ss2 = entityManager.persistAndFlush(new SalaryStory("SalaryStory2"));
        ss3 = entityManager.persistAndFlush(new SalaryStory("SalaryStory3"));

        ef1 = entityManager.persistAndFlush(new Effectiveness("Effectiveness1"));
        ef2 = entityManager.persistAndFlush(new Effectiveness("Effectiveness2"));
        ef3 = entityManager.persistAndFlush(new Effectiveness("Effectiveness3"));

        scb1 = entityManager.persistAndFlush(new ScoreClientBase("ScoreClientBase1"));
        scb2 = entityManager.persistAndFlush(new ScoreClientBase("ScoreClientBase2"));
        scb3 = entityManager.persistAndFlush(new ScoreClientBase("ScoreClientBase3"));

        e = Employee.builder()
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

        e = entityManager.persistAndFlush(e);
    }

    @Test
    public void testSets() {
        e.getKeySkills().add(ks1);
        e.getSalaryStories().add(ss1);
        e.getEffectiveness().add(ef1);
        e.getScoreClientBase().add(scb1);
        employeeRepository.save(e);

        Employee empl = employeeRepository.findById(e.getId()).get();
        assertThat(empl.getKeySkills().size()).isEqualTo(1);
        assertThat(empl.getSalaryStories().size()).isEqualTo(1);
        assertThat(empl.getEffectiveness().size()).isEqualTo(1);
        assertThat(empl.getScoreClientBase().size()).isEqualTo(1);

        empl.getKeySkills().add(ks2);
        empl.getKeySkills().remove(ks1);
        empl.getSalaryStories().add(ss2);
        empl.getSalaryStories().remove(ss1);
        empl.getEffectiveness().add(ef2);
        empl.getEffectiveness().remove(ef1);
        empl.getScoreClientBase().add(scb2);
        empl.getScoreClientBase().remove(scb1);
        employeeRepository.save(empl);

        empl = employeeRepository.findById(e.getId()).get();
        assertThat(empl.getKeySkills().size()).isEqualTo(1);
        assertThat(empl.getKeySkills()).contains(ks2);
        assertThat(empl.getSalaryStories().size()).isEqualTo(1);
        assertThat(empl.getSalaryStories()).contains(ss2);
        assertThat(empl.getEffectiveness().size()).isEqualTo(1);
        assertThat(empl.getEffectiveness()).contains(ef2);
        assertThat(empl.getScoreClientBase().size()).isEqualTo(1);
        assertThat(empl.getScoreClientBase()).contains(scb2);
    }


}
