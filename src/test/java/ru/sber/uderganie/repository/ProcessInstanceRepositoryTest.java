package ru.sber.uderganie.repository;

import org.apache.tomcat.jni.Proc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sber.uderganie.Util;
import ru.sber.uderganie.model.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProcessInstanceRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProcessInstanceRepository processInstanceRepository;

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ClaimRepository claimRepository;

    @Test
    public void testProcessInstanceWithTask() throws Exception {
        Task ta = new Task();
        ta = taskRepository.save(ta);

        ProcessInstance pi = ProcessInstance.builder().build();
        pi = processInstanceRepository.save(pi);

        ta.setProcessInstance(pi);
        ta = taskRepository.save(ta);

        pi.getTasks().add(ta);
        pi = processInstanceRepository.save(pi);

        Task t2 = taskRepository.findById(ta.getId()).get();
        System.out.println(t2.getProcessInstance());
        assertThat(t2).hasFieldOrPropertyWithValue("processInstance", pi);
    }

    @Test
    public void testSettingActive() {

        Employee e = Util.buildEmployee();
        e.setEmployeeId("123");
        employeeRepository.save(e);

        Claim c = Util.buildClaim();
        c.setEmployeeId("123");
        claimRepository.save(c);

        ProcessInstance pi = ProcessInstance.builder()
                .active(true)
                .claim(c)
                .employee(e)
                .status(ProcessInstance.Status.NEW)
                .build();
        processInstanceRepository.save(pi);
        pi = ProcessInstance.builder()
                .active(true)
                .claim(c)
                .employee(e)
                .status(ProcessInstance.Status.NEW)
                .build();
        processInstanceRepository.save(pi);
        pi = ProcessInstance.builder()
                .active(true)
                .claim(c)
                .employee(e)
                .status(ProcessInstance.Status.NEW)
                .build();
        processInstanceRepository.save(pi);


        entityManager.getEntityManager().createQuery("select p " +
                "from ProcessInstance p " +
                "where p.active = true ").getResultStream()
                .forEach(p -> {
                    System.out.println(((ProcessInstance) p).getStatus());
                });

        assertThat(processInstanceRepository.countByEmployee_EmployeeIdAndActiveTrue("123")).isEqualTo(3l);

        processInstanceRepository.markAllProcessInstancesActive(e, false);

        assertThat(processInstanceRepository.countByEmployee_EmployeeIdAndActiveTrue("123")).isEqualTo(0l);


    }
}
