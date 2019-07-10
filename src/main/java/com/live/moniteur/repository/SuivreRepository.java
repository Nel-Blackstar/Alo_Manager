package com.live.moniteur.repository;

import com.live.moniteur.entities.Inscription;
import com.live.moniteur.entities.Suivre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuivreRepository extends JpaRepository<Suivre, Long> {
    List<Suivre> findAllByInscription(Inscription inscription);
}
