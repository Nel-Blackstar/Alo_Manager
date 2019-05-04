package com.live.paie.service;

import com.live.paie.entities.Avances;

import java.util.List;

public interface AvancesService {
    Avances save(Avances avances);
    void delete(Avances avances);
    Avances findOne(Long id);
    List<Avances> findAll();
}
