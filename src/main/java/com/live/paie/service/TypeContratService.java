package com.live.paie.service;

import com.live.paie.entities.TypeContrat;

import java.util.List;

public interface TypeContratService {
    TypeContrat save(TypeContrat typeContrat);
    void delete(TypeContrat typeContrat);
    TypeContrat findOne(Long id);
    List<TypeContrat> findAll();
}
