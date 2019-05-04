package com.live.paie.service;

import com.live.paie.entities.TypeConge;

import java.util.List;

public interface TypeCongeService {
    TypeConge save(TypeConge typeConge);
    void delete(TypeConge typeConge);
    TypeConge findOne(Long id);
    List<TypeConge> findAll();
}
