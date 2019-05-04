package com.live.paie.service;

import com.live.paie.entities.Credits;

import java.util.List;

public interface CreditsService {
    Credits save(Credits credits);
    void delete(Credits credits);
    Credits findOne(Long id);
    List<Credits> findAll();
}
