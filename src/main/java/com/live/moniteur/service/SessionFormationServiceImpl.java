package com.live.moniteur.service;

import com.live.moniteur.entities.SessionFormation;
import com.live.moniteur.repository.SessionFormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionFormationServiceImpl implements SessionFormationService{
    @Autowired
    SessionFormationRepository sessionFormationRepository;
    @Override
    public SessionFormation save(SessionFormation sessionF) {
        return sessionFormationRepository.save(sessionF);
    }

    @Override
    public void delete(SessionFormation sessionF) {
        if (sessionFormationRepository.getOne(sessionF.getId()) != null) {
        	sessionFormationRepository.delete(sessionF);
        } else {
            new RuntimeException("entity doesn't exist");
        }

    }

    @Override
    public SessionFormation findOne(Long id) {
        return sessionFormationRepository.getOne(id);
    }

    @Override
    public List<SessionFormation> findAll() {
        return sessionFormationRepository.findAll();
    }
}
