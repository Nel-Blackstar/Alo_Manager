package com.live.paie.service;

import com.live.paie.entities.ParametreEntreprise;
import com.live.paie.repository.ParametreEntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParametreEntrepriseServiceImpl implements ParametreEntrepriseService {
    @Autowired
    private ParametreEntrepriseRepository parametreEntrepriseRepository;
    @Override
    public ParametreEntreprise save(ParametreEntreprise parametreEntreprise) {
        return parametreEntrepriseRepository.save(parametreEntreprise);
    }

    @Override
    public void delete(ParametreEntreprise parametreEntreprise) {
        if (parametreEntrepriseRepository.getOne(parametreEntreprise.getId()) != null) {
            parametreEntrepriseRepository.delete(parametreEntreprise);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public ParametreEntreprise findOne(Long id) {
        return parametreEntrepriseRepository.getOne(id);
    }

    @Override
    public List<ParametreEntreprise> findAll() {
        return parametreEntrepriseRepository.findAll();
    }
}
