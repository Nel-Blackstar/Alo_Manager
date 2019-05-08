package com.live.paie.service;

import com.live.paie.entities.Anciennete;

import java.util.List;

public interface AncienneteService {
    Anciennete save(Anciennete anciennete);
    void delete(Anciennete anciennete);
    Anciennete findOne(Long id);
    List<Anciennete> findAll();
}
