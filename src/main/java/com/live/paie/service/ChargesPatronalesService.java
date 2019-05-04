package com.live.paie.service;

import com.live.paie.entities.ChargesPatronales;

import java.util.List;

public interface ChargesPatronalesService {
    ChargesPatronales save(ChargesPatronales chargesPatronales);
    void delete(ChargesPatronales chargesPatronales);
    ChargesPatronales findOne(Long id);
    List<ChargesPatronales> findAll();
}
