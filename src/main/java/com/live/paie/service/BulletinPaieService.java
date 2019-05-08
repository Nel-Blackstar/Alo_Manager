package com.live.paie.service;

import com.live.paie.entities.BulletinPaie;

import java.util.List;

public interface BulletinPaieService {
    BulletinPaie save(BulletinPaie bulletinPaie);
    void delete(BulletinPaie bulletinPaie);
    BulletinPaie findOne(Long id);
    List<BulletinPaie> findAll();
}
