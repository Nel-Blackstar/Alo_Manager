package com.live.rh.service;

import com.live.rh.entities.Fournitures;

import java.util.List;

public interface FournituresService {
    Fournitures save(Fournitures fournitures);
    void delete(Fournitures fournitures);
    Fournitures findOne(Long id);
    List<Fournitures> findAll();
}
