package com.live.paie.service;

import com.live.paie.entities.ChargesPatronales;
import com.live.paie.repository.ChargesPatronalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargesPatronalesServiceImpl implements ChargesPatronalesService {
    @Autowired
    private ChargesPatronalesRepository chargesPatronalesRepository;
    @Override
    public ChargesPatronales save(ChargesPatronales chargesPatronales) {
        return chargesPatronalesRepository.save(chargesPatronales);
    }

    @Override
    public void delete(ChargesPatronales chargesPatronales) {
        if (chargesPatronalesRepository.getOne(chargesPatronales.getId()) != null) {
            chargesPatronalesRepository.delete(chargesPatronales);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public ChargesPatronales findOne(Long id) {
        return chargesPatronalesRepository.getOne(id);
    }

    @Override
    public List<ChargesPatronales> findAll() {
        return chargesPatronalesRepository.findAll();
    }
}
