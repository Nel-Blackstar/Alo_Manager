package com.live.core.service;

import com.live.core.entities.Partenaire;
import com.live.core.repository.PartenaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartenaireServiceImpl implements PartenaireService {
    @Autowired
    PartenaireRepository partenaireRepository;
    @Override
    public Partenaire save(Partenaire partenaire) {
        return partenaireRepository.save(partenaire);
    }

    @Override
    public void delete(Partenaire partenaire) {
        if (partenaireRepository.getOne(partenaire.getId()) != null) {
            partenaireRepository.delete(partenaire);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Partenaire findOne(Long id) {
        return partenaireRepository.getOne(id);
    }

    @Override
    public List<Partenaire> findAll() {
        return partenaireRepository.findAll();
    }
}
