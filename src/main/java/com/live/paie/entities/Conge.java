package com.live.paie.entities;

import com.live.common.entities.LiveEntity;
import com.live.core.entities.Personnel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Conge extends LiveEntity {
    private Long duree;
    private String description;
    @ManyToOne(optional = true, targetEntity = Personnel.class)
    private Personnel personnel;

    @ManyToOne(optional = true, targetEntity = TypeConge.class)
    private TypeConge typeConge;

    public Conge() {
    }

    public Conge(Long duree, String description) {
        this.duree = duree;
        this.description = description;
    }

    public Long getDuree() {
        return duree;
    }

    public void setDuree(Long duree) {
        this.duree = duree;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
