package com.live.rh.service;

import com.live.rh.entities.Facture;
import com.live.rh.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureServiceImpl implements FactureService {
    @Autowired
    private FactureRepository factureRepository;
    @Override
    public Facture save(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public void delete(Facture facture) {
        if (factureRepository.getOne(facture.getId()) != null) {
            factureRepository.delete(facture);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Facture findOne(Long id) {
        return factureRepository.getOne(id);
    }

    @Override
    public List<Facture> findAll() {
        return factureRepository.findAll();
    }
}
