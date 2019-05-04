package com.live.paie.service;

import com.live.paie.entities.Contrat;

import java.util.List;

public interface ContratService {
    Contrat save(Contrat contrat);
    void delete(Contrat contrat);
    Contrat findOne(Long id);
    List<Contrat> findAll();
}
