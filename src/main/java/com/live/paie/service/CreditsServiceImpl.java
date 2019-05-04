package com.live.paie.service;

import com.live.paie.entities.Credits;
import com.live.paie.repository.CreditsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditsServiceImpl implements CreditsService {
    @Autowired
    CreditsRepository creditsRepository;
    @Override
    public Credits save(Credits credits) {
        return creditsRepository.save(credits);
    }

    @Override
    public void delete(Credits credits) {
        if (creditsRepository.getOne(credits.getId()) != null) {
            creditsRepository.delete(credits);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Credits findOne(Long id) {
        return creditsRepository.getOne(id);
    }

    @Override
    public List<Credits> findAll() {
        return creditsRepository.findAll();
    }
}
