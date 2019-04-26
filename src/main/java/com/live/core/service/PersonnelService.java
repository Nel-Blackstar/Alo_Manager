package com.live.core.service;

import com.live.core.entities.Personnel;

import java.util.List;

public interface PersonnelService {
    Personnel save(Personnel personnel);
    void delete(Personnel personnel);
    Personnel findOne(Long id);
    List<Personnel> findAll();
}
