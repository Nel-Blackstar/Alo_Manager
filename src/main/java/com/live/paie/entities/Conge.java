package com.live.paie.entities;

import com.live.common.entities.LiveEntity;
import com.live.core.entities.Personnel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Conge extends LiveEntity {
    private Long duree;
    private String description;
    @ManyToOne(optional = true, targetEntity = Personnel.class)
    private Personnel personnel;

    @OneToOne(targetEntity = TypeConge.class)
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

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public TypeConge getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(TypeConge typeConge) {
        this.typeConge = typeConge;
    }
}
