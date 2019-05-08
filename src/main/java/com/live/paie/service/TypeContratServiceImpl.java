package com.live.paie.service;

import com.live.paie.entities.TypeContrat;
import com.live.paie.repository.TypeContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeContratServiceImpl implements TypeContratService {
    @Autowired
    private TypeContratRepository typeContratRepository;
    @Override
    public TypeContrat save(TypeContrat typeContrat) {
        return typeContratRepository.save(typeContrat);
    }

    @Override
    public void delete(TypeContrat typeContrat) {
        if (typeContratRepository.getOne(typeContrat.getId()) != null) {
            typeContratRepository.delete(typeContrat);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public TypeContrat findOne(Long id) {
        return typeContratRepository.getOne(id);
    }

    @Override
    public List<TypeContrat> findAll() {
        return typeContratRepository.findAll();
    }
}
