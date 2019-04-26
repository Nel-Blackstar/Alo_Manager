package com.live.core.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Users implements Serializable {

    @Id
    @Column(unique = true)
    private String login;

    private String username;

    private String password;
    private Boolean active;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;



    @ManyToMany(targetEntity = Roles.class)
    @JoinTable(name="users_roles", joinColumns = {
            @JoinColumn(name = "login")}, inverseJoinColumns = {
            @JoinColumn(name = "id_role")})
    List<Roles> roles;

    public Users() {
        this.createdAt = new Date();
    }

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
        this.createdAt = new Date();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public void addRole(Roles role) {
        getRoles().add(role);
    }

    public void removeRole(Roles role){
        getRoles().remove(role);
    }
}
