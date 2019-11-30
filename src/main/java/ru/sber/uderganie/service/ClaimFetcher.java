package ru.sber.uderganie.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.sber.uderganie.model.*;
import ru.sber.uderganie.repository.ClaimRepository;
import ru.sber.uderganie.repository.ProcessDefinitionRepository;
import ru.sber.uderganie.repository.ProcessInstanceRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClaimFetcher {

    Logger log = LoggerFactory.getLogger(ClaimFetcher.class);

    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    ProcessDefinitionRepository processDefinitionRepository;

    @Autowired
    ProcessInstanceRepository processInstanceRepository;

    @Autowired
    EmployeeService employeeService;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void doFetch() {
        List<Claim> newClaims = claimRepository.findAllByClaimStatus(Claim.ClaimStatus.NEW);

        ProcessDefinition processDefinition = processDefinitionRepository.findByType(ProcessDefinition.ProcessType.STAY_INTERVIEW);

        for (Claim claim : newClaims) {

            Employee employee = employeeService.findEmployee(claim.getEmployeeId());

            ProcessInstance newProcess = new ProcessInstance();
            newProcess.setClaim(claim);
            newProcess.setDefinition(processDefinition);
            newProcess.setStatus(Status.NEW);
            newProcess.setEmployee(employee);

            claim.setClaimStatus(Claim.ClaimStatus.PROCESSED);
            claim.setProcessInstance(newProcess);
            claimRepository.save(claim);


            processInstanceRepository.save(newProcess);
            log.info("Claim "+claim.getId()+" processed. ProcessInstance created.");
        }
    }
}
