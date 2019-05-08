package com.live.rh.service;

import com.live.rh.entities.RendezVous;
import com.live.rh.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RendezVousServiceImpl implements RendezVousService {
    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Override
    public RendezVous save(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public void delete(RendezVous rendezVous) {
        if (rendezVousRepository.getOne(rendezVous.getId()) != null) {
            rendezVousRepository.delete(rendezVous);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public RendezVous findOne(Long id) {
        return rendezVousRepository.getOne(id);
    }

    @Override
    public List<RendezVous> findAll() {
        return rendezVousRepository.findAll();
    }
}
