package com.live.paie.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;

@Entity
public class ParametreEntreprise extends LiveEntity {
    private float nbreHeureJour;
    private float nbreJourMois;
    private float tauxTravailMinimum;

    public ParametreEntreprise() {
    }

    public ParametreEntreprise(float nbreHeureJour, float nbreJourMois, float tauxTravailMinimum) {
        this.nbreHeureJour = nbreHeureJour;
        this.nbreJourMois = nbreJourMois;
        this.tauxTravailMinimum = tauxTravailMinimum;
    }

    public float getNbreHeureJour() {
        return nbreHeureJour;
    }

    public void setNbreHeureJour(float nbreHeureJour) {
        this.nbreHeureJour = nbreHeureJour;
    }

    public float getNbreJourMois() {
        return nbreJourMois;
    }

    public void setNbreJourMois(float nbreJourMois) {
        this.nbreJourMois = nbreJourMois;
    }

    public float getTauxTravailMinimum() {
        return tauxTravailMinimum;
    }

    public void setTauxTravailMinimum(float tauxTravailMinimum) {
        this.tauxTravailMinimum = tauxTravailMinimum;
    }
}
