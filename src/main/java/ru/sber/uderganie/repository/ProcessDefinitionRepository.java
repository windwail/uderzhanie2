package ru.sber.uderganie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.uderganie.model.ProcessDefinition;

@Repository
public interface ProcessDefinitionRepository extends JpaRepository<ProcessDefinition, Long> {

    ProcessDefinition findByType(ProcessDefinition.ProcessType type);
}
