package com.live.paie.service;

import com.live.paie.entities.Enfants;
import com.live.paie.repository.EnfantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnfantsServiceImpl implements EnfantsService {
    @Autowired
    private EnfantsRepository enfantsRepository;
    @Override
    public Enfants save(Enfants enfant) {
        return enfantsRepository.save(enfant);
    }

    @Override
    public void delete(Enfants enfant) {
        if (enfantsRepository.getOne(enfant.getId()) != null) {
            enfantsRepository.delete(enfant);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Enfants findOne(Long id) {
        return enfantsRepository.getOne(id);
    }

    @Override
    public List<Enfants> findAll() {
        return enfantsRepository.findAll();
    }
}
