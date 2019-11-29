package ru.sber.uderganie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.uderganie.model.KeySkill;

@Repository
public interface KeySkillRepository extends JpaRepository<KeySkill, Long> {
}
