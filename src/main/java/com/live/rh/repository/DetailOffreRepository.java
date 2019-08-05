package com.live.rh.repository;


import com.live.rh.entities.Details;
import com.live.rh.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailOffreRepository extends JpaRepository<Details, Long> {
    List<Details> findAllByOffre(Offre offre);
}
