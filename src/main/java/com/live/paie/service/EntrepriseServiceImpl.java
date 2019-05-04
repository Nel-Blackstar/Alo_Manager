package com.live.paie.service;

import com.live.paie.entities.Entreprise;
import com.live.paie.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrepriseServiceImpl implements EntrepriseService {
    @Autowired
    EntrepriseRepository entrepriseRepository;
    @Override
    public Entreprise save(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public void delete(Entreprise entreprise) {
        if (entrepriseRepository.getOne(entreprise.getId()) != null) {
            entrepriseRepository.delete(entreprise);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Entreprise findOne(Long id) {
        return entrepriseRepository.getOne(id);
    }

    @Override
    public List<Entreprise> findAll() {
        return entrepriseRepository.findAll();
    }
}
