package com.live.core.service;

import com.live.core.entities.Roles;
import com.live.core.entities.Users;
import com.live.core.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users save(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public void delete(Users users) {
        if (usersRepository.findUsersByLogin(users.getLogin()) != null) {
            usersRepository.delete(users);
        } else {
            new RuntimeException("entity doesn't exist");
        }

    }

    @Override
    public Users findOne(Long id) {
        return usersRepository.getOne(id);
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findByLogin(String login) {
        return usersRepository.findUsersByLogin(login);
    }

    @Override
    public long utilisateursCount() {
        return usersRepository.count();
    }

    @Override
    public List<Users> findUsersByRole(Roles roles) {
        return usersRepository.findAllByRoles(roles);
    }
}
