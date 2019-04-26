package com.live.core.service;

import com.live.core.entities.Roles;
import com.live.core.entities.Users;

import java.util.List;

public interface UsersService {
    Users save(Users users);
    void delete(Users users);
    Users findOne(Long id);
    List<Users> findAll();
    Users findByLogin(String login);
    long utilisateursCount();
    List<Users> findUsersByRole(Roles roles);
}
