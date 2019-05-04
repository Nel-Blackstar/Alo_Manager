package com.live.rh.service;

import com.live.rh.entities.RendezVous;

import java.util.List;

public interface RendezVousService {
    RendezVous save(RendezVous rendezVous);
    void delete(RendezVous rendezVous);
    RendezVous findOne(Long id);
    List<RendezVous> findAll();
}
