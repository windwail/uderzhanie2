package ru.sber.uderganie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.uderganie.model.ProcessInstance;

@Repository
public interface ProcessInstanceRepository extends JpaRepository<ProcessInstance, Long> {
}
