package com.live.moniteur.service;

import com.live.moniteur.entities.SessionFormation;

import java.util.List;

public interface SessionFormationService {
    SessionFormation save(SessionFormation sessionFormation);
    void delete(SessionFormation sessionFormation);
    SessionFormation findOne(Long id);
    List<SessionFormation> findAll();
}
