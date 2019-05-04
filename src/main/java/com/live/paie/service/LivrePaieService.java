package com.live.paie.service;

import com.live.paie.entities.LivrePaie;

import java.util.List;

public interface LivrePaieService {
    LivrePaie save(LivrePaie livrePaie);
    void delete(LivrePaie livrePaie);
    LivrePaie findOne(Long id);
    List<LivrePaie> findAll();
}
