package com.live.core.service;

import com.live.core.entities.Parametre;

import java.util.List;

public interface ParametreService {
    Parametre save(Parametre parametre);
    void delete(Parametre parametre);
    Parametre findOne(Long id);
    List<Parametre> findAll();
}
