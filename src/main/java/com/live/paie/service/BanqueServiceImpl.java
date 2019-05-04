package com.live.paie.service;

import com.live.paie.entities.Banque;
import com.live.paie.repository.BanqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BanqueServiceImpl implements BanqueService {
    @Autowired
    private BanqueRepository banqueRepository;
    @Override
    public Banque save(Banque banque) {
        return banqueRepository.save(banque);
    }

    @Override
    public void delete(Banque banque) {
        if (banqueRepository.getOne(banque.getId()) != null) {
            banqueRepository.delete(banque);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public Banque findOne(Long id) {
        return banqueRepository.getOne(id);
    }

    @Override
    public List<Banque> findAll() {
        return banqueRepository.findAll();
    }
}
