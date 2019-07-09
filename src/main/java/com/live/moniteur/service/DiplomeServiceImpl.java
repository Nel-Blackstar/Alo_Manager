package com.live.moniteur.service;

import com.live.moniteur.entities.Diplome;
import com.live.moniteur.repository.DiplomeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiplomeServiceImpl implements DiplomeService{
    @Autowired
    DiplomeRepository diplomeRepository;
    @Override
    public Diplome save(Diplome diplome) {
        return diplomeRepository.save(diplome);
    }

    @Override
    public void delete(Diplome diplome) {
        if (diplomeRepository.getOne(diplome.getId()) != null) {
        	diplomeRepository.delete(diplome);
        } else {
            new RuntimeException("entity doesn't exist");
        }

    }

    @Override
    public Diplome findOne(Long id) {
        return diplomeRepository.getOne(id);
    }

    @Override
    public List<Diplome> findAll() {
        return diplomeRepository.findAll();
    }
}
