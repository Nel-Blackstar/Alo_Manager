package com.live.core.service;

import com.live.core.entities.Roles;
import com.live.core.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RolesServiceImpl implements RolesService {
    @Autowired
    RolesRepository rolesRepository;
    @Override
    public Roles save(Roles roles) {
        return rolesRepository.save(roles);
    }

    @Override
    public void delete(Roles roles) {
        if (rolesRepository.getOne(roles.getNom_role()) != null){
            rolesRepository.delete(roles);
        } else {
            new RuntimeException("entity doesn't exist");
        }

    }

    @Override
    public Roles findOne(String id) {
        return rolesRepository.getOne(id);
    }

    @Override
    public List<Roles> findAll() {
        return rolesRepository.findAll();
    }
}
