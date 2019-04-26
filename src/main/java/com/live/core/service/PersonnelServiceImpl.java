package com.live.core.service;

import com.live.core.entities.Personnel;
import com.live.core.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelServiceImpl implements PersonnelService {
    @Autowired
    PersonnelRepository personnelRepository;
    @Override
    public Personnel save(Personnel personnel) {
        return personnelRepository.save(personnel);
    }

    @Override
    public void delete(Personnel personnel) {
        if (personnelRepository.getOne(personnel.getId()) != null) {
            personnelRepository.delete(personnel);
        } else {
            new RuntimeException("entity doesn't exist");
        }

    }

    @Override
    public Personnel findOne(Long id) {
        return personnelRepository.getOne(id);
    }

    @Override
    public List<Personnel> findAll() {
        return personnelRepository.findAll();
    }
}
