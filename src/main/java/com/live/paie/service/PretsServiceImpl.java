package com.live.paie.service;

import com.live.paie.entities.Prets;
import com.live.paie.repository.PretsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PretsServiceImpl implements PretsService {
    @Autowired
    PretsRepository pretsRepository;
    @Override
    public Prets save(Prets prets) {
        return pretsRepository.save(prets);
    }

    @Override
    public void delete(Prets prets) {
        if (pretsRepository.getOne(prets.getId()) != null) {
            pretsRepository.delete(prets);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Prets findOne(Long id) {
        return pretsRepository.getOne(id);
    }

    @Override
    public List<Prets> findAll() {
        return pretsRepository.findAll();
    }
}
