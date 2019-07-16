package com.live.moniteur.service;

import com.live.moniteur.entities.Inscription;
import com.live.moniteur.entities.SessionFormation;
import com.live.moniteur.repository.InscriptionRepository;
import com.live.rh.entities.Apprenant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscriptionServiceImpl implements InscriptionService{
    @Autowired
    InscriptionRepository inscriptionRepository;
    @Override
    public Inscription save(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    @Override
    public void delete(Inscription inscription) {
        if (inscriptionRepository.getOne(inscription.getId()) != null) {
        	inscriptionRepository.delete(inscription);
        } else {
            new RuntimeException("entity doesn't exist");
        }

    }

    @Override
    public Inscription findOne(Long id) {
        return inscriptionRepository.getOne(id);
    }

    @Override
    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }
    @Override
    public List<Inscription> findInscriptionsByFormation(SessionFormation formation) {
        return inscriptionRepository.findAllByFormation(formation);
    }

    @Override
    public List<Inscription> findInscriptionsByApprenant(Apprenant apprenant) {
        return inscriptionRepository.findByApprenant(apprenant);
    }
}
