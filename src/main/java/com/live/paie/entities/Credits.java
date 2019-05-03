package com.live.paie.entities;

import com.live.common.entities.LiveEntity;
import com.live.core.entities.Personnel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Credits extends LiveEntity {
    private Long valeur;
    private Long retenue;
    private Long tauxInteret;
    @ManyToOne(optional = true, targetEntity = Personnel.class)
    private Personnel personnel;

    public Credits() {
    }

    public Credits(Long valeur, Long retenue, Long tauxInteret) {
        this.valeur = valeur;
        this.retenue = retenue;
        this.tauxInteret = tauxInteret;
    }

    public Long getValeur() {
        return valeur;
    }

    public void setValeur(Long valeur) {
        this.valeur = valeur;
    }

    public Long getRetenue() {
        return retenue;
    }

    public void setRetenue(Long retenue) {
        this.retenue = retenue;
    }

    public Long getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(Long tauxInteret) {
        this.tauxInteret = tauxInteret;
    }
}
