package com.live.paie.service;

import com.live.paie.entities.Profession;
import com.live.paie.repository.ProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionServiceImpl implements ProfessionService {
    @Autowired
    ProfessionRepository professionRepository;
    @Override
    public Profession save(Profession profession) {
        return professionRepository.save(profession);
    }

    @Override
    public void delete(Profession profession) {
        if (professionRepository.getOne(profession.getId()) != null) {
            professionRepository.delete(profession);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Profession findOne(Long id) {
        return professionRepository.getOne(id);
    }

    @Override
    public List<Profession> findAll() {
        return professionRepository.findAll();
    }
}
