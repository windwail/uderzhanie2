package ru.sber.uderganie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.uderganie.model.ProcessInstance;
import ru.sber.uderganie.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByActiveTrue();

}
