package com.live.rh.service;

import com.live.rh.entities.Offre;

import java.util.List;

public interface OffreService {
    Offre save(Offre offre);
    void delete(Offre offre);
    Offre findOne(Long id);
    List<Offre> findAll();
}
