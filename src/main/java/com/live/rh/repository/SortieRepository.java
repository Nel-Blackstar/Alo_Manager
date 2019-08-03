package com.live.rh.repository;

import com.live.rh.entities.Offre;
import com.live.rh.entities.Sortie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SortieRepository extends JpaRepository<Sortie, Long> {
    List<Sortie> findAllByOffre(Offre offre);
}
