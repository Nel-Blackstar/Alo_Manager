package com.live.core.service;

import com.live.core.entities.Roles;

import java.util.List;

public interface RolesService {
    Roles save(Roles roles);
    void delete(Roles roles);
    Roles findOne(String id);
    List<Roles> findAll();
}
