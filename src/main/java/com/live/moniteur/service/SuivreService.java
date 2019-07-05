package com.live.moniteur.service;

import com.live.moniteur.entities.Suivre;

import java.util.List;

public interface SuivreService {
    Suivre save(Suivre suivre);
    void delete(Suivre suivre);
    Suivre findOne(Long id);
    List<Suivre> findAll();
}
