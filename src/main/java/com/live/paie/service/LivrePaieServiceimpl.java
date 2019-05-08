package com.live.paie.service;

import com.live.paie.entities.LivrePaie;
import com.live.paie.repository.LivrePaieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivrePaieServiceimpl implements LivrePaieService {
    @Autowired
    private LivrePaieRepository livrePaieRepository;
    @Override
    public LivrePaie save(LivrePaie livrePaie) {
        return livrePaieRepository.save(livrePaie);
    }

    @Override
    public void delete(LivrePaie livrePaie) {
        if (livrePaieRepository.getOne(livrePaie.getId()) != null) {
            livrePaieRepository.delete(livrePaie);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public LivrePaie findOne(Long id) {
        return livrePaieRepository.getOne(id);
    }

    @Override
    public List<LivrePaie> findAll() {
        return livrePaieRepository.findAll();
    }
}
