package com.live.moniteur.repository;

import com.live.moniteur.entities.Inscription;
import com.live.moniteur.entities.SessionFormation;
import com.live.rh.entities.Apprenant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
	List<Inscription> findAllByFormation(SessionFormation formation);

    List<Inscription> findByApprenant(Apprenant apprenant);
}
