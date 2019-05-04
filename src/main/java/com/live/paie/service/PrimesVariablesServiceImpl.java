package com.live.paie.service;

import com.live.paie.entities.PrimesVariables;
import com.live.paie.repository.PrimesVariablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrimesVariablesServiceImpl implements PrimesVariablesService {
    @Autowired
    private PrimesVariablesRepository primesVariablesRepository;
    @Override
    public PrimesVariables save(PrimesVariables primesVariables) {
        return primesVariablesRepository.save(primesVariables);
    }

    @Override
    public void delete(PrimesVariables primesVariables) {
        if (primesVariablesRepository.getOne(primesVariables.getId()) != null) {
            primesVariablesRepository.delete(primesVariables);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public PrimesVariables findOne(Long id) {
        return primesVariablesRepository.getOne(id);
    }

    @Override
    public List<PrimesVariables> findAll() {
        return primesVariablesRepository.findAll();
    }
}
