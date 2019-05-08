package com.live.moniteur.service;

import com.live.moniteur.entities.Chapitre;

import java.util.List;

public interface ChapitreService {
    Chapitre save(Chapitre chapitre);
    void delete(Chapitre chapitre);
    Chapitre findOne(Long id);
    List<Chapitre> findAll();
}
