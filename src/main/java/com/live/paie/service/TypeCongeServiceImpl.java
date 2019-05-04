package com.live.paie.service;

import com.live.paie.entities.TypeConge;
import com.live.paie.repository.TypeCongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeCongeServiceImpl implements TypeCongeService {
    @Autowired
    TypeCongeRepository typeCongeRepository;
    @Override
    public TypeConge save(TypeConge typeConge) {
        return typeCongeRepository.save(typeConge);
    }

    @Override
    public void delete(TypeConge typeConge) {
        if (typeCongeRepository.getOne(typeConge.getId()) != null) {
            typeCongeRepository.delete(typeConge);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public TypeConge findOne(Long id) {
        return typeCongeRepository.getOne(id);
    }

    @Override
    public List<TypeConge> findAll() {
        return typeCongeRepository.findAll();
    }
}
