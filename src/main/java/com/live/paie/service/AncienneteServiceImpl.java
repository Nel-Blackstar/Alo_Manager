package com.live.paie.service;

import com.live.paie.entities.Anciennete;
import com.live.paie.repository.AncienneteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AncienneteServiceImpl implements AncienneteService {
    @Autowired
    private AncienneteRepository ancienneteRepository;
    @Override
    public Anciennete save(Anciennete anciennete) {
        return ancienneteRepository.save(anciennete);
    }

    @Override
    public void delete(Anciennete anciennete) {
        if (ancienneteRepository.getOne(anciennete.getId()) != null) {
            ancienneteRepository.delete(anciennete);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Anciennete findOne(Long id) {
        return ancienneteRepository.getOne(id);
    }

    @Override
    public List<Anciennete> findAll() {
        return ancienneteRepository.findAll();
    }
}
