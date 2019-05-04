package com.live.rh.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;

@Entity
public class Prevision extends LiveEntity {
    private String type;
    private  String description;
    private Long quantite;

    public Prevision() {
    }

    public Prevision(String type, String description, Long quantite) {
        this.type = type;
        this.description = description;
        this.quantite = quantite;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }
}
