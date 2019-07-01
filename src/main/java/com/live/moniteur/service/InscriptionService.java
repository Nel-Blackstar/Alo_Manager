package com.live.moniteur.service;

import com.live.moniteur.entities.Inscription;
import com.live.moniteur.entities.SessionFormation;
import com.live.rh.entities.Apprenant;

import java.util.List;

public interface InscriptionService {
    Inscription save(Inscription inscription);
    void delete(Inscription inscription);
    Inscription findOne(Long id);
    List<Inscription> findAll();
    List<Inscription> findInscriptionsByFormation(SessionFormation formation);
}
