package com.live.paie.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;

@Entity
public class Mutuelle extends LiveEntity {
    private float tauxSalarial;
    private float tauxPatronal;

    public Mutuelle() {
    }

    public Mutuelle(float tauxSalarial, float tauxPatronal) {
        this.tauxSalarial = tauxSalarial;
        this.tauxPatronal = tauxPatronal;
    }

    public float getTauxSalarial() {
        return tauxSalarial;
    }

    public void setTauxSalarial(float tauxSalarial) {
        this.tauxSalarial = tauxSalarial;
    }

    public float getTauxPatronal() {
        return tauxPatronal;
    }

    public void setTauxPatronal(float tauxPatronal) {
        this.tauxPatronal = tauxPatronal;
    }
}
