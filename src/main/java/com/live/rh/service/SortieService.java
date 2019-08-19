package com.live.rh.service;

import com.live.rh.entities.Facture;
import com.live.rh.entities.Offre;
import com.live.rh.entities.Sortie;

import java.util.List;

public interface SortieService {
    Sortie save(Sortie sortie);
    void delete(Sortie sortie);
    Sortie findOne(Long id);
    List<Sortie> findAll();
    List<Sortie> findAllByOffre(Offre offre);
    List<Sortie> findAllByFacture(Facture facture);
}
