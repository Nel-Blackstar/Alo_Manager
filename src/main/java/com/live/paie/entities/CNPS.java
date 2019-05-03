package com.live.paie.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;

@Entity
public class CNPS extends LiveEntity {
    private float tauxSalarial;
    private float plafondSalarial;
    private float tauxPatronal;

    public CNPS() {
    }

    public CNPS(float tauxSalarial, float plafondSalarial, float tauxPatronal) {
        this.tauxSalarial = tauxSalarial;
        this.plafondSalarial = plafondSalarial;
        this.tauxPatronal = tauxPatronal;
    }

    public float getTauxSalarial() {
        return tauxSalarial;
    }

    public void setTauxSalarial(float tauxSalarial) {
        this.tauxSalarial = tauxSalarial;
    }

    public float getPlafondSalarial() {
        return plafondSalarial;
    }

    public void setPlafondSalarial(float plafondSalarial) {
        this.plafondSalarial = plafondSalarial;
    }

    public float getTauxPatronal() {
        return tauxPatronal;
    }

    public void setTauxPatronal(float tauxPatronal) {
        this.tauxPatronal = tauxPatronal;
    }
}
