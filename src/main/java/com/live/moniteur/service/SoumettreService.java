package com.live.moniteur.service;

import com.live.moniteur.entities.Soumettre;

import java.util.List;

public interface SoumettreService {
    Soumettre save(Soumettre soumettre);
    void delete(Soumettre soumettre);
    Soumettre findOne(Long id);
    List<Soumettre> findAll();
}
