package com.live.core.service;

import com.live.core.entities.Historique;
import com.live.core.repository.HistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoriqueServiceImpl implements HistoriqueService {
    @Autowired
    HistoriqueRepository historiqueRepository;
    @Override
    public Historique save(Historique historique) {
        return historiqueRepository.save(historique);
    }

    @Override
    public void delete(Historique historique) {
        if (historiqueRepository.getOne(historique.getId()) != null) {
            historiqueRepository.delete(historique);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Historique findOne(Long id) {
        return historiqueRepository.getOne(id);
    }

    @Override
    public List<Historique> findAll() {
        return historiqueRepository.findAll();
    }
}
