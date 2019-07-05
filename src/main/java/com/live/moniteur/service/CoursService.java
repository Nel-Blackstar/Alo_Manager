package com.live.moniteur.service;

import com.live.moniteur.entities.Cours;
import com.live.moniteur.entities.SessionFormation;

import java.util.List;

public interface CoursService {
    Cours save(Cours cours);
    void delete(Cours cours);
    Cours findOne(Long id);
    List<Cours> findAll();
    List<Cours> findByFormation(SessionFormation formation);
}
