package com.live.rh.service;

import com.live.rh.entities.Offre;
import com.live.rh.entities.Sortie;
import com.live.rh.repository.SortieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortieServiceImpl implements SortieService{
    @Autowired
    private SortieRepository sortieRepository;
    @Override
    public Sortie save(Sortie sortie) {
        return sortieRepository.save(sortie);
    }

    @Override
    public void delete(Sortie sortie) {
        if (sortieRepository.getOne(sortie.getId()) != null) {
            sortieRepository.delete(sortie);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Sortie findOne(Long id) {
        return sortieRepository.getOne(id);
    }

    @Override
    public List<Sortie> findAll() {
        return sortieRepository.findAll();
    }

    @Override
    public List<Sortie> findAllByOffre(Offre offre) {
        return sortieRepository.findAllByOffre(offre);
    }
}
