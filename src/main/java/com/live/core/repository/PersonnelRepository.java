package com.live.core.repository;

import com.live.core.entities.Personnel;
import com.live.core.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    //liste des Personnels n'ayant pas de compte
    @Query("SELECT p FROM Personnel p WHERE p.nom NOT IN(SELECT u.username FROM Users u)")
    List<Personnel> findUsersAccount();
}
