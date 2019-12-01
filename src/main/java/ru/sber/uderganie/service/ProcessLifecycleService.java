package ru.sber.uderganie.service;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.MetaClass;
import org.hibernate.jpa.QueryHints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.sber.uderganie.model.InterviewResult;
import ru.sber.uderganie.model.ProcessDefinition;
import ru.sber.uderganie.model.ProcessInstance;
import ru.sber.uderganie.model.Task;
import ru.sber.uderganie.repository.ProcessInstanceRepository;
import ru.sber.uderganie.repository.TaskRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class ProcessLifecycleService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    ProcessInstanceRepository repository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    TaskRepository taskRepository;


    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void doFetch() {

        LocalDateTime now = LocalDateTime.now();

        entityManager.createQuery("select p " +
                "from ProcessInstance p " +
                "where p.active = true ").getResultStream()
                .forEach(o -> {
                    ProcessInstance pi = (ProcessInstance) o;
                    Set<Task> tasks = pi.getTasks();

                    // 1. Если нет тасков (никаких) - создаем таск руководителю.
                    if (tasks.isEmpty()) {

                        Task t = Task.builder()
                                .chiefId(pi.getChiefId())
                                .type(Task.TaskType.CHIEF)
                                .active(true)
                                .status(Task.TaskStatus.SCHEDULED)
                                .doUntil(now.plusSeconds(pi.getClaim().getInterval()))
                                .build();

                        pi.getTasks().add(t);
                        repository.save(pi);

                    } else {
                        for (Task t : tasks) {
                            // Если таск активный
                            if (t.getActive()) {
                                if (t.getResult() != null) {
                                    // 1. Если есть результат интервью

                                    // Помечаем как выполненное.
                                    t.setStatus(Task.TaskStatus.DONE);
                                    t.setActive(false);
                                    pi.getTasks().remove(t);
                                    taskRepository.save(t);

                                    InterviewResult result = t.getResult();

                                    // Если проведено текущим руководителем.
                                    if (Task.TaskType.CHIEF.equals(t.getType())) {

                                        switch (result.getResult()) {
                                            case CANT_HOLD:
                                            case NOT_APPROPRIATE:
                                                // Не удержали - передаем следующему.
                                                Task newTask = Task.builder()
                                                        .processInstance(pi)
                                                        .chiefId(pi.getChiefPlusId())
                                                        .type(Task.TaskType.CHIEFPLUS)
                                                        .active(true)
                                                        .doUntil(now.plusSeconds(pi.getClaim().getInterval()))
                                                        .status(Task.TaskStatus.SCHEDULED)
                                                        .build();
                                                taskRepository.save(newTask);
                                                pi.getTasks().add(newTask);
                                                break;
                                            case HOLDED:
                                                // Удержали. Завершаем процесс.
                                                pi.setStatus(ProcessInstance.Status.DONE_HOLDED);
                                                pi.setActive(false);
                                                break;
                                        }

                                        repository.save(pi);

                                    } else if (Task.TaskType.CHIEFPLUS.equals(t.getType())) {

                                        switch (result.getResult()) {
                                            case CANT_HOLD:
                                                // Удержали. Завершаем процесс.
                                                pi.setStatus(ProcessInstance.Status.DONE_CANT_HOLD);
                                                pi.setActive(false);
                                                break;
                                            case NOT_APPROPRIATE:
                                                // Удержали. Завершаем процесс.
                                                pi.setStatus(ProcessInstance.Status.DONE_NOT_APPROPRIATE);
                                                pi.setActive(false);
                                                break;
                                            case HOLDED:
                                                // Удержали. Завершаем процесс.
                                                pi.setStatus(ProcessInstance.Status.DONE_HOLDED);
                                                pi.setActive(false);
                                                break;
                                        }
                                        repository.save(pi);
                                    } else {
                                        log.error("Not supposed to be here");
                                    }
                                } else {  // t.getResult() == null
                                    // 2. Если нет результата интервью - проверяем просрочен или нет.
                                    if (t.getDoUntil().isAfter(now)) {
                                        // Делаем текущий статус неактивным.
                                        t.setStatus(Task.TaskStatus.OUTDATED);
                                        t.setActive(false);
                                        taskRepository.save(t);

                                        // Если просрочено текущим руководителем.
                                        if (Task.TaskType.CHIEF.equals(t.getType())) {
                                            Task newTask = Task.builder()
                                                    .processInstance(pi)
                                                    .chiefId(pi.getChiefPlusId())
                                                    .type(Task.TaskType.CHIEFPLUS)
                                                    .active(true)
                                                    .doUntil(now.plusSeconds(pi.getClaim().getInterval()))
                                                    .status(Task.TaskStatus.SCHEDULED)
                                                    .build();
                                            taskRepository.save(newTask);
                                            pi.getTasks().add(newTask);
                                        } else if (Task.TaskType.CHIEFPLUS.equals(t.getType())) {
                                            // Если просрочено руководителем+.
                                            pi.setStatus(ProcessInstance.Status.DONE_OUTDATED);
                                            pi.setActive(false);
                                        } else {
                                            log.error("Not supposed to be here");
                                        }

                                        repository.save(pi);
                                    } else {
                                        // Не просрочен
                                        // Нет результатов интервью
                                        // Ничего не делаем.
                                    }
                                }


                            }
                        }
                    }
                });
    }

}
