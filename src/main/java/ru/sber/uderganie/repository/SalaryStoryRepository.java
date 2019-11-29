package ru.sber.uderganie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.uderganie.model.SalaryStory;

@Repository
public interface SalaryStoryRepository extends JpaRepository<SalaryStory, Long> {
}
