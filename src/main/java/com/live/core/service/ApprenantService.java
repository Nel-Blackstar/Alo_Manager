package com.live.core.service;

import com.live.core.entities.Apprenant;

import java.util.List;

public interface ApprenantService {
    Apprenant save(Apprenant apprenant);
    void delete(Apprenant apprenant);
    Apprenant findOne(Long id);
    List<Apprenant> findAll();
}
