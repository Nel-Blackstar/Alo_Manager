package com.live.paie.entities;

import com.live.common.entities.LiveEntity;
import com.live.core.entities.Personnel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Avances extends LiveEntity {
    private Long valeur;
    @ManyToOne(optional = true, targetEntity = Personnel.class)
    private Personnel personnel;

    public Avances() {
    }

    public Avances(Long valeur) {
        this.valeur = valeur;
    }

    public Long getValeur() {
        return valeur;
    }

    public void setValeur(Long valeur) {
        this.valeur = valeur;
    }
}
