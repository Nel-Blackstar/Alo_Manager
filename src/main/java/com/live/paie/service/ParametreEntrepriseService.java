package com.live.paie.service;

import com.live.paie.entities.ParametreEntreprise;

import java.util.List;

public interface ParametreEntrepriseService {
    ParametreEntreprise save(ParametreEntreprise parametreEntreprise);
    void delete(ParametreEntreprise parametreEntreprise);
    ParametreEntreprise findOne(Long id);
    List<ParametreEntreprise> findAll();
}
