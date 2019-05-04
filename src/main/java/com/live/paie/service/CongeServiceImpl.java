package com.live.paie.service;

import com.live.paie.entities.Conge;
import com.live.paie.repository.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CongeServiceImpl implements CongeService {
    @Autowired
    private CongeRepository congeRepository;
    @Override
    public Conge save(Conge conge) {
        return congeRepository.save(conge);
    }

    @Override
    public void delete(Conge conge) {
        if (congeRepository.getOne(conge.getId()) != null) {
            congeRepository.delete(conge);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Conge findOne(Long id) {
        return congeRepository.getOne(id);
    }

    @Override
    public List<Conge> findAll() {
        return congeRepository.findAll();
    }
}
