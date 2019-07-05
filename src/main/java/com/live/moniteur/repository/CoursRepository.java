package com.live.moniteur.repository;

import com.live.moniteur.entities.Cours;
import com.live.moniteur.entities.SessionFormation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursRepository extends JpaRepository<Cours, Long> {
	List<Cours> findAllByFormation(SessionFormation formation);
}
