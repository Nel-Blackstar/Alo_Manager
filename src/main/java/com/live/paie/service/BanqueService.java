package com.live.paie.service;

import com.live.paie.entities.Banque;

import java.util.List;

public interface BanqueService {
    Banque save(Banque banque);
    void delete(Banque banque);
    Banque findOne(Long id);
    List<Banque> findAll();
}
