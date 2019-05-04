package com.live.paie.service;

import com.live.paie.entities.Entreprise;

import java.util.List;

public interface EntrepriseService {
    Entreprise save(Entreprise entreprise);
    void delete(Entreprise entreprise);
    Entreprise findOne(Long id);
    List<Entreprise> findAll();
}
