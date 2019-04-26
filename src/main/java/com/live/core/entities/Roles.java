package com.live.core.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Roles {
    @Id
    private String nom_role;

    // List users
    @ManyToMany(targetEntity = Users.class)
    @JoinTable(name="users_roles", joinColumns = {
            @JoinColumn(name = "id_role")}, inverseJoinColumns = {
            @JoinColumn(name = "login")})
    private List<Users> users;

    public Roles() {
    }

    public String getNom_role() {
        return nom_role;
    }

    public void setNom_role(String nom_role) {
        this.nom_role = nom_role;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public void addUser(Users user){
        getUsers().add(user);
    }

    public void removeUser(Users user){
        getUsers().remove(user);
    }
}
