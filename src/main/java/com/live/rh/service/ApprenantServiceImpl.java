package com.live.rh.service;

import com.live.rh.entities.Apprenant;
import com.live.rh.repository.ApprenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprenantServiceImpl implements ApprenantService{
    @Autowired
    private ApprenantRepository apprenantRepository;
    @Override
    public Apprenant save(Apprenant apprenant) {
        return apprenantRepository.save(apprenant);
    }

    @Override
    public void delete(Apprenant apprenant) {
        if (apprenantRepository.getOne(apprenant.getId()) != null) {
            apprenantRepository.delete(apprenant);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Apprenant findOne(Long id) {
        return apprenantRepository.getOne(id);
    }

    @Override
    public List<Apprenant> findAll() {
        return apprenantRepository.findAll();
    }
}
