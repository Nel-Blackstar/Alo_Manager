package com.live.moniteur.service;

import com.live.moniteur.entities.Diplome;

import java.util.List;

public interface DiplomeService {
    Diplome save(Diplome diplome);
    void delete(Diplome diplome);
    Diplome findOne(Long id);
    List<Diplome> findAll();
}
