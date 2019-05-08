package com.live.core.service;

import com.live.core.entities.Partenaire;

import java.util.List;

public interface PartenaireService  {
    Partenaire save(Partenaire partenaire);
    void delete(Partenaire partenaire);
    Partenaire findOne(Long id);
    List<Partenaire> findAll();
}
