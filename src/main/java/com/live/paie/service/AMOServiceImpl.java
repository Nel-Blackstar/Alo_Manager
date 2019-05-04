package com.live.paie.service;

import com.live.paie.entities.AMO;
import com.live.paie.repository.AMORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AMOServiceImpl implements AMOService {
    @Autowired
    AMORepository amoRepository;
    @Override
    public AMO save(AMO amo) {
        return amoRepository.save(amo);
    }

    @Override
    public void delete(AMO amo) {
        if (amoRepository.getOne(amo.getId()) != null) {
            amoRepository.delete(amo);
        } else {
            new RuntimeException("entity doesn't exist");
        }
    }

    @Override
    public AMO findOne(Long id) {
        return amoRepository.getOne(id);
    }

    @Override
    public List<AMO> findAll() {
        return amoRepository.findAll();
    }
}
