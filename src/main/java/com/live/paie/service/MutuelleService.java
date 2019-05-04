package com.live.paie.service;

import com.live.paie.entities.Mutuelle;

import java.util.List;

public interface MutuelleService {
    Mutuelle save(Mutuelle mutuelle);
    void delete(Mutuelle mutuelle);
    Mutuelle findOne(Long id);
    List<Mutuelle> findAll();
}
