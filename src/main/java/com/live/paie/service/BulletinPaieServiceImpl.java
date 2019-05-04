package com.live.paie.service;

import com.live.paie.entities.BulletinPaie;
import com.live.paie.repository.BulletinPaieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulletinPaieServiceImpl implements BulletinPaieService {
    @Autowired
    BulletinPaieRepository bulletinPaieRepository;
    @Override
    public BulletinPaie save(BulletinPaie bulletinPaie) {
        return bulletinPaieRepository.save(bulletinPaie);
    }

    @Override
    public void delete(BulletinPaie bulletinPaie) {
        if (bulletinPaieRepository.getOne(bulletinPaie.getId()) != null) {
            bulletinPaieRepository.delete(bulletinPaie);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public BulletinPaie findOne(Long id) {
        return bulletinPaieRepository.getOne(id);
    }

    @Override
    public List<BulletinPaie> findAll() {
        return bulletinPaieRepository.findAll();
    }
}
