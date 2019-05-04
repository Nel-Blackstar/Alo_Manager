package com.live.paie.entities;

import com.live.common.entities.LiveEntity;
import com.live.core.entities.Personnel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Prets extends LiveEntity {
    private Long valeur;
    private Long retenueMensuelle;
    @ManyToOne(optional = true, targetEntity = Personnel.class)
    private Personnel personnel;

    public Prets() {
    }

    public Prets(Long valeur, Long retenueMensuelle) {
        this.valeur = valeur;
        this.retenueMensuelle = retenueMensuelle;
    }

    public Long getValeur() {
        return valeur;
    }

    public void setValeur(Long valeur) {
        this.valeur = valeur;
    }

    public Long getRetenueMensuelle() {
        return retenueMensuelle;
    }

    public void setRetenueMensuelle(Long retenueMensuelle) {
        this.retenueMensuelle = retenueMensuelle;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }


}
