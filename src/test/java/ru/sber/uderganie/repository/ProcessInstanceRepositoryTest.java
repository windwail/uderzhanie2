package ru.sber.uderganie.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sber.uderganie.model.ProcessInstance;
import ru.sber.uderganie.model.Task;

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

    @Test
    public void testProcessInstanceWithTask() throws Exception {


        Task ta = new Task();
        ta= taskRepository.save(ta);

        ProcessInstance pi = new ProcessInstance();
        pi = processInstanceRepository.save(pi);

        ta.setProcessInstance(pi);
        ta = taskRepository.save(ta);

        pi.getTasks().add(ta);
        pi = processInstanceRepository.save(pi);

        Task t2 = taskRepository.findById(ta.getId()).get();
        System.out.println(t2.getProcessInstance());
        assertThat(t2).hasFieldOrPropertyWithValue("processInstance", pi);

    }
}
