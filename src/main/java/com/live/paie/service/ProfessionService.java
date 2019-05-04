package com.live.paie.service;

import com.live.paie.entities.Profession;

import java.util.List;

public interface ProfessionService {
    Profession save(Profession profession);
    void delete(Profession profession);
    Profession findOne(Long id);
    List<Profession> findAll();
}
