package com.live.common.service;

import com.live.common.entities.Detail;

import java.util.List;

public interface DetailService {

    Detail save(Detail detail);

    void delete(Detail detail);

    Detail findOne(String id);

    Detail findOne(Long id);

    Detail findByNom(String nom);

    //find detail by label of detail
    Detail findByLabel(String label);

    List<Detail> findAll();

    List<Detail> findDetailByNom_entite(String entity_name);
}
