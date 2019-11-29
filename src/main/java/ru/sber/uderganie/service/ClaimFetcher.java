package ru.sber.uderganie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.sber.uderganie.model.Claim;
import ru.sber.uderganie.repository.ClaimRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClaimFetcher {

    @Autowired
    ClaimRepository claimRepository;

    @Scheduled(fixedDelay = 1000)
    public void doFetch() {
        List<Claim> newClaims = claimRepository.findAllByProcessInstanceIsNull();
    }
}
