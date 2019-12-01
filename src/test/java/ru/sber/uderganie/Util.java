package ru.sber.uderganie;

import ru.sber.uderganie.model.Claim;
import ru.sber.uderganie.model.Employee;

import java.time.LocalDate;
import java.util.HashSet;

public class Util {

    public static Employee buildEmployee() {
        return Employee.builder()
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

    }

    public static Claim buildClaim() {
        return Claim.builder()
                .claimId("claimId")
                .claimStatus(Claim.ClaimStatus.NEW)
                .employeeId("employeeId")
                .employeeReason("employeeReason")
                .executionStatus("executionStatus")
                .fireDate(LocalDate.now())
                .fireType("fireType")
                .processInstance(null)
                .build();
    }
}
