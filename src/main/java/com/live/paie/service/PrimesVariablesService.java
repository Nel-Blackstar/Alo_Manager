package com.live.paie.service;

import com.live.paie.entities.PrimesVariables;

import java.util.List;

public interface PrimesVariablesService {
    PrimesVariables save(PrimesVariables primesVariables);
    void delete(PrimesVariables primesVariables);
    PrimesVariables findOne(Long id);
    List<PrimesVariables> findAll();
}
