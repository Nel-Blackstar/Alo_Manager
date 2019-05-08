package com.live.paie.service;

import com.live.paie.entities.Conge;

import java.util.List;

public interface CongeService {
    Conge save(Conge conge);
    void delete(Conge conge);
    Conge findOne(Long id);
    List<Conge> findAll();
}
