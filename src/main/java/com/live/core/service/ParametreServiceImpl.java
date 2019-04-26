package com.live.core.service;

import com.live.core.entities.Parametre;
import com.live.core.repository.ParametreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParametreServiceImpl implements ParametreService {
    @Autowired
    ParametreRepository parametreRepository;
    @Override
    public Parametre save(Parametre parametre) {
        return parametreRepository.save(parametre);
    }

    @Override
    public void delete(Parametre parametre) {
        if (parametreRepository.getOne(parametre.getId()) != null) {
            parametreRepository.delete(parametre);
        } else {
            new RuntimeException("entity doesn't exist");
        }

    }

    @Override
    public Parametre findOne(Long id) {
        return parametreRepository.getOne(id);
    }

    @Override
    public List<Parametre> findAll() {
        return parametreRepository.findAll();
    }
}
