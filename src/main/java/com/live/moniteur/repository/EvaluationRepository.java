package com.live.moniteur.repository;

import com.live.moniteur.entities.Evaluation;
import com.live.moniteur.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findAllByInscription(Inscription inscription);
}
