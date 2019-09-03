package com.live.paie.repository;

import com.live.core.entities.Personnel;
import com.live.paie.entities.Enfants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnfantsRepository extends JpaRepository<Enfants, Long> {
    List<Enfants> findByPersonnel(Personnel personnel);
}
