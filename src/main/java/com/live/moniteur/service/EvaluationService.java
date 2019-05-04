package com.live.moniteur.service;

import com.live.moniteur.entities.Evaluation;

import java.util.List;

public interface EvaluationService {
    Evaluation save(Evaluation evaluation);
    void delete(Evaluation evaluation);
    Evaluation findOne(Long id);
    List<Evaluation> findAll();
}
