package ru.sber.uderganie;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sber.uderganie.model.Claim;
import ru.sber.uderganie.model.ProcessDefinition;
import ru.sber.uderganie.model.ProcessInstance;
import ru.sber.uderganie.model.Task;
import ru.sber.uderganie.repository.*;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UderganieApplicationTests {

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


    @Test
    public void contextLoads() {
//        ProcessDefinition pd = new ProcessDefinition();
//        pd.setType(ProcessDefinition.ProcessType.STAY_INTERVIEW);
//        processDefinitionRepository.save(pd);

        LocalDate now = LocalDate.now();

        ProcessInstance pi = new ProcessInstance();
        processInstanceRepository.save(pi);

        Claim c = Claim.builder()
                .claimId("claimId")
                .claimStatus("claimStatus")
                .employeeId("employeeId")
                .employeeReason("employeeReason")
                .executionStatus("executionStatus")
                .fireDate(now)
                .fireType("fireType")
                .processInstance(pi)
                .build();

        claimRepository.save(c);

        Claim c2 = Claim.builder()
                .claimId("claimId")
                .claimStatus("claimStatus")
                .employeeId("employeeId")
                .employeeReason("employeeReason")
                .executionStatus("executionStatus")
                .fireDate(now)
                .fireType("fireType")
                .build();

        claimRepository.save(c2);

        List<Claim> newClaims = claimRepository.findAllByProcessInstanceIsNull();
        assertThat(newClaims).containsOnly(c2);

    }

}
