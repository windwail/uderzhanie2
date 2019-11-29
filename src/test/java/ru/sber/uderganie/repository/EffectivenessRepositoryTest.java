package ru.sber.uderganie.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sber.uderganie.model.Effectiveness;
import ru.sber.uderganie.model.Employee;

import java.time.LocalDate;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EffectivenessRepositoryTest {


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EffectivenessRepository effectivenessRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void testRepo() {
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

        e.getEffectiveness().add(e1);
        e.getEffectiveness().add(e2);
        employeeRepository.save(e);

        Employee emp = employeeRepository.findById(e.getId()).get();

        assertThat(emp.getEffectiveness()).containsExactly(e1,e2);

    }
}
