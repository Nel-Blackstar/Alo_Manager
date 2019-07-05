package com.live.moniteur.service;

import com.live.moniteur.entities.Chapitre;
import com.live.moniteur.repository.ChapitreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapitreServiceImpl implements ChapitreService{
    @Autowired
    ChapitreRepository chapitreRepository;

    @Override
    public Chapitre save(Chapitre chapitre) {
        return chapitreRepository.save(chapitre);
    }

    @Override
    public void delete(Chapitre chapitre) {
                chapitreRepository.delete(chapitre);
    }

    @Override
    public Chapitre findOne(Long id) {
        return chapitreRepository.getOne(id);
    }

    @Override
    public List<Chapitre> findAll() {
        return chapitreRepository.findAll();
    }

}
