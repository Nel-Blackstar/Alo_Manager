package com.live.rh.service;

import com.live.rh.entities.Facture;

import java.util.List;

public interface FactureService {
    Facture save(Facture facture);
    void delete(Facture facture);
    Facture findOne(Long id);
    List<Facture> findAll();
}
