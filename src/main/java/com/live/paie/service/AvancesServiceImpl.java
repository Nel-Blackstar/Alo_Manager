package com.live.paie.service;

import com.live.paie.entities.Avances;
import com.live.paie.repository.AvancesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvancesServiceImpl implements AvancesService {
    @Autowired
    private AvancesRepository avancesRepository;
    @Override
    public Avances save(Avances avances) {
        return avancesRepository.save(avances);
    }

    @Override
    public void delete(Avances avances) {
        if (avancesRepository.getOne(avances.getId()) != null) {
            avancesRepository.delete(avances);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Avances findOne(Long id) {
        return avancesRepository.getOne(id);
    }

    @Override
    public List<Avances> findAll() {
        return avancesRepository.findAll();
    }
}
