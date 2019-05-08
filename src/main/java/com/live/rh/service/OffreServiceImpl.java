package com.live.rh.service;

import com.live.rh.entities.Offre;
import com.live.rh.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffreServiceImpl implements OffreService {
    @Autowired
    private OffreRepository offreRepository;
    @Override
    public Offre save(Offre offre) {
        return offreRepository.save(offre);
    }

    @Override
    public void delete(Offre offre) {
        if (offreRepository.getOne(offre.getId()) != null) {
            offreRepository.delete(offre);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Offre findOne(Long id) {
        return offreRepository.getOne(id);
    }

    @Override
    public List<Offre> findAll() {
        return offreRepository.findAll();
    }
}
