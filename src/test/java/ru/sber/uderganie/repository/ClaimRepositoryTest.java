package ru.sber.uderganie.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sber.uderganie.model.Claim;
import ru.sber.uderganie.model.ProcessInstance;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClaimRepositoryTest {

    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    ProcessInstanceRepository processInstanceRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void testClaim() throws Exception {
        LocalDate now = LocalDate.now();

        ProcessInstance pi =  ProcessInstance.builder().build();
        pi = entityManager.persistAndFlush(pi);

        Claim c = Claim.builder()
                .claimId("claimId")
                .claimStatus(Claim.ClaimStatus.NEW)
                .employeeId("employeeId")
                .employeeReason("employeeReason")
                .executionStatus("executionStatus")
                .fireDate(now)
                .fireType("fireType")
                .processInstance(pi)
                .build();

        Long id = entityManager.persistAndGetId(c, Long.class);

        c = claimRepository.findById(id).get();

        assertThat(c)
                .hasFieldOrPropertyWithValue("claimId", "claimId")
                .hasFieldOrPropertyWithValue("claimStatus", "claimStatus")
                .hasFieldOrPropertyWithValue("employeeId", "employeeId")
                .hasFieldOrPropertyWithValue("employeeReason", "employeeReason")
                .hasFieldOrPropertyWithValue("executionStatus", "executionStatus")
                .hasFieldOrPropertyWithValue("fireType", "fireType")
                .hasFieldOrPropertyWithValue("processInstance", pi)
                .hasFieldOrPropertyWithValue("fireDate", now);

    }

    @Test
    public void testFindAllByProcessInstanceIsNull() throws Exception {

        LocalDate now = LocalDate.now();

        ProcessInstance pi = ProcessInstance.builder().build();
        processInstanceRepository.save(pi);

        Claim c = Claim.builder()
                .claimId("claimId")
                .claimStatus(Claim.ClaimStatus.NEW)
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
                .claimStatus(Claim.ClaimStatus.NEW)
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
