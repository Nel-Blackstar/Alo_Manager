package com.live.moniteur.service;

import com.live.moniteur.entities.Cours;
import com.live.moniteur.entities.Inscription;
import com.live.moniteur.entities.SessionFormation;
import com.live.moniteur.repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursServiceImpl implements CoursService{
    @Autowired
    CoursRepository coursRepository;
    @Override
    public Cours save(Cours cours) {
        return  coursRepository.save(cours);
    }

    @Override
    public void delete(Cours cours) {
         coursRepository.delete(cours);
    }

    @Override
    public Cours findOne(Long id) {
        return coursRepository.getOne(id);
    }

    @Override
    public List<Cours> findAll() {
        return coursRepository.findAll();
    }

    public List<Cours> findByFormation(SessionFormation formation) {
        return coursRepository.findAllByFormation(formation);
    }
}
