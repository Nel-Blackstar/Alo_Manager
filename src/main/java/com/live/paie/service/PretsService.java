package com.live.paie.service;

import com.live.paie.entities.Prets;

import java.util.List;

public interface PretsService {
    Prets save(Prets prets);
    void delete(Prets prets);
    Prets findOne(Long id);
    List<Prets> findAll();
}
