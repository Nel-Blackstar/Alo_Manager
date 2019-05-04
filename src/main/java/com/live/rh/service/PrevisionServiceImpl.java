package com.live.rh.service;

import com.live.rh.entities.Prevision;
import com.live.rh.repository.PrevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrevisionServiceImpl implements PrevisionService {
    @Autowired
    private PrevisionRepository previsionRepository;
    @Override
    public Prevision save(Prevision prevision) {
        return previsionRepository.save(prevision);
    }

    @Override
    public void delete(Prevision prevision) {
        if (previsionRepository.getOne(prevision.getId()) != null) {
            previsionRepository.delete(prevision);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Prevision findOne(Long id) {
        return previsionRepository.getOne(id);
    }

    @Override
    public List<Prevision> findAll() {
        return previsionRepository.findAll();
    }
}
