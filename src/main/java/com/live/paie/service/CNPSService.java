package com.live.paie.service;

import com.live.paie.entities.CNPS;

import java.util.List;

public interface CNPSService {
    CNPS save(CNPS cnps);
    void delete(CNPS cnps);
    CNPS findOne(Long id);
    List<CNPS> findAll();
}
