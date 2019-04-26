package com.live.common.repository;

import com.live.common.entities.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Long> {

    @Query("SELECT d FROM Detail d WHERE d.nom =:nom")
    Detail findDetailByNom(@Param("nom")String nom);
    @Query("SELECT d FROM Detail d WHERE d.label =:label")
    Detail findDetailByLabel(@Param("label")String label);
    @Override
    List<Detail> findAll();
    @Query("SELECT d FROM Detail d WHERE d.nom =:nom")
    List<Detail> findDetailByNom_entite(@Param("nom") String nom_entite);
}
