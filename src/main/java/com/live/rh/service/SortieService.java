package com.live.rh.service;

import com.live.rh.entities.Sortie;

import java.util.List;

public interface SortieService {
    Sortie save(Sortie sortie);
    void delete(Sortie sortie);
    Sortie findOne(Long id);
    List<Sortie> findAll();
}
