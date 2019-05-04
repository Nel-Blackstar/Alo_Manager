package com.live.paie.service;

import com.live.paie.entities.Conge;
import com.live.paie.entities.Contrat;
import com.live.paie.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratServiceImpl implements ContratService {
    @Autowired
    ContratRepository contratRepository;

    @Override
    public Contrat save(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public void delete(Contrat contrat) {
        if (contratRepository.getOne(contrat.getId()) != null) {
            contratRepository.delete(contrat);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Contrat findOne(Long id) {
        return contratRepository.getOne(id);
    }

    @Override
    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }
}
