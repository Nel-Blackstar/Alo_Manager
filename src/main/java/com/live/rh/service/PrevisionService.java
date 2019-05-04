package com.live.rh.service;

import com.live.rh.entities.Prevision;

import java.util.List;

public interface PrevisionService {
    Prevision save(Prevision prevision);
    void delete(Prevision prevision);
    Prevision findOne(Long id);
    List<Prevision> findAll();
}
