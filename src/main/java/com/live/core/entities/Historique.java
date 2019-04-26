package com.live.core.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Historique extends LiveEntity {

    private String action;
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    Date date_action;

    @OneToOne(targetEntity = Users.class)
    Users user;

    public Historique() {
    }

    public Historique(String action, String description, Date date_action) {
        this.action = action;
        this.description = description;
        this.date_action = date_action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_action() {
        return date_action;
    }

    public void setDate_action(Date date_action) {
        this.date_action = date_action;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
