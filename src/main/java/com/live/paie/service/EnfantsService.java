package com.live.paie.service;

import com.live.paie.entities.Enfants;

import java.util.List;

public interface EnfantsService {
    Enfants save(Enfants enfants);
    void delete(Enfants enfants);
    Enfants findOne(Long id);
    List<Enfants> findAll();
}
