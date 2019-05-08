package com.live.rh.service;

import com.live.rh.entities.Fournitures;
import com.live.rh.repository.FournituresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FournituresServiceImpl implements FournituresService {
    @Autowired
    private FournituresRepository fournituresRepository;
    @Override
    public Fournitures save(Fournitures fournitures) {
        return fournituresRepository.save(fournitures);
    }

    @Override
    public void delete(Fournitures fournitures) {
        if (fournituresRepository.getOne(fournitures.getId()) != null) {
            fournituresRepository.delete(fournitures);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Fournitures findOne(Long id) {
        return fournituresRepository.getOne(id);
    }

    @Override
    public List<Fournitures> findAll() {
        return fournituresRepository.findAll();
    }
}
