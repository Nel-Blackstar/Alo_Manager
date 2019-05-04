package com.live.paie.service;

import com.live.paie.entities.PrimesFixes;

import java.util.List;

public interface PrimesFixesService {
    PrimesFixes save(PrimesFixes primesFixes);
    void delete(PrimesFixes primesFixes);
    PrimesFixes findOne(Long id);
    List<PrimesFixes> findAll();
}
