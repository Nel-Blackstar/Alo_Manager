package com.live.moniteur.service;

import com.live.moniteur.entities.Inscription;

import java.util.List;

public interface InscriptionService {
    Inscription save(Inscription inscription);
    void delete(Inscription inscription);
    Inscription findOne(Long id);
    List<Inscription> findAll();
}
