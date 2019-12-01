package ru.sber.uderganie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sber.uderganie.model.Employee;
import ru.sber.uderganie.model.ProcessInstance;

import java.util.List;

@Repository
public interface ProcessInstanceRepository extends JpaRepository<ProcessInstance, Long> {

    Long countByEmployee_EmployeeIdAndActiveTrue(String id);

    List<ProcessInstance> findAllByActiveTrueAndEmployee_EmployeeId(String id);

    List<ProcessInstance> findAllByActiveTrue();

    @Modifying(clearAutomatically = true)
    @Query("update ProcessInstance pi set pi.active =:active, pi.status = 'INTERRUPTED' where pi.employee =:employee")
    void markAllProcessInstancesActive(@Param("employee") Employee employee, @Param("active") boolean isActive);
}
