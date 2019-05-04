package com.live.paie.service;

import com.live.paie.entities.CNPS;
import com.live.paie.repository.CNPSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CNPSServiceImpl implements CNPSService {
    @Autowired
    CNPSRepository cnpsRepository;
    @Override
    public CNPS save(CNPS cnps) {
        return cnpsRepository.save(cnps);
    }

    @Override
    public void delete(CNPS cnps) {
        if (cnpsRepository.getOne(cnps.getId()) != null) {
            cnpsRepository.delete(cnps);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public CNPS findOne(Long id) {
        return cnpsRepository.getOne(id);
    }

    @Override
    public List<CNPS> findAll() {
        return cnpsRepository.findAll();
    }
}
