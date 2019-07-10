package com.live.moniteur.service;

import com.live.moniteur.entities.Inscription;
import com.live.moniteur.entities.Suivre;
import com.live.moniteur.repository.SuivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuivreServiceImpl implements SuivreService{
    @Autowired
    SuivreRepository suivreRepository;
    @Override
    public Suivre save(Suivre suivre) {
        return suivreRepository.save(suivre);
    }

    @Override
    public void delete(Suivre suivre) {
        if (suivreRepository.getOne(suivre.getId()) != null) {
        	suivreRepository.delete(suivre);
        } else {
            new RuntimeException("entity doesn't exist");
        }

    }

    @Override
    public Suivre findOne(Long id) {
        return suivreRepository.getOne(id);
    }

    @Override
    public List<Suivre> findAll() {
        return suivreRepository.findAll();
    }

    @Override
    public List<Suivre> findAllByInscription(Inscription inscription) {
        return suivreRepository.findAllByInscription(inscription);
    }
}
