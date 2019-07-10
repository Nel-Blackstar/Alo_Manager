package com.live.moniteur.service;

import com.live.moniteur.entities.Inscription;
import com.live.moniteur.entities.Evaluation;
import com.live.moniteur.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService{
    @Autowired
    EvaluationRepository evaluationRepository;
    @Override
    public Evaluation save(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public void delete(Evaluation evaluation) {
        if (evaluationRepository.getOne(evaluation.getId()) != null) {
            evaluationRepository.delete(evaluation);
        } else {
            new RuntimeException("entity doesn't exist");
        }

    }

    @Override
    public Evaluation findOne(Long id) {
        return evaluationRepository.getOne(id);
    }

    @Override
    public List<Evaluation> findAll() {
        return evaluationRepository.findAll();
    }

    @Override
    public List<Evaluation> findAllByInscription(Inscription inscription) {
        return evaluationRepository.findAllByInscription(inscription);
    }

}
