package com.live.paie.service;

import com.live.paie.entities.Mutuelle;
import com.live.paie.repository.MutuelleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MutuelleServiceImpl implements MutuelleService {
    @Autowired
    MutuelleRepository mutuelleRepository;
    @Override
    public Mutuelle save(Mutuelle mutuelle) {
        return mutuelleRepository.save(mutuelle);
    }

    @Override
    public void delete(Mutuelle mutuelle) {
        if (mutuelleRepository.getOne(mutuelle.getId()) != null) {
            mutuelleRepository.delete(mutuelle);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Mutuelle findOne(Long id) {
        return mutuelleRepository.getOne(id);
    }

    @Override
    public List<Mutuelle> findAll() {
        return mutuelleRepository.findAll();
    }
}
