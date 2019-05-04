package com.live.paie.service;

import com.live.paie.entities.PrimesFixes;
import com.live.paie.repository.PrimesFixesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrimesFixesServiceImpl implements PrimesFixesService {
    @Autowired
    PrimesFixesRepository primesFixesRepository;
    @Override
    public PrimesFixes save(PrimesFixes primesFixes) {
        return primesFixesRepository.save(primesFixes);
    }

    @Override
    public void delete(PrimesFixes primesFixes) {
        if (primesFixesRepository.getOne(primesFixes.getId()) != null) {
            primesFixesRepository.delete(primesFixes);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public PrimesFixes findOne(Long id) {
        return primesFixesRepository.getOne(id);
    }

    @Override
    public List<PrimesFixes> findAll() {
        return primesFixesRepository.findAll();
    }
}
