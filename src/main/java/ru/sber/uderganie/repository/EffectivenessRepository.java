package ru.sber.uderganie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.uderganie.model.Effectiveness;

@Repository
public interface EffectivenessRepository extends JpaRepository<Effectiveness, Long> {
}
