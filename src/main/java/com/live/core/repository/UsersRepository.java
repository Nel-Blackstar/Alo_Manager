package com.live.core.repository;

import com.live.core.entities.Roles;
import com.live.core.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u WHERE u.login =:login")
    Users findUsersByLogin(@Param("login")String login);

    List<Users> findAllByRoles(Roles roles);
}
