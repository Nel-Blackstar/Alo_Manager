package com.live.core.service;

import com.live.core.entities.Historique;

import java.util.List;

public interface HistoriqueService {
    Historique save(Historique historique);
    void delete(Historique historique);
    Historique findOne(Long id);
    List<Historique> findAll();
}
