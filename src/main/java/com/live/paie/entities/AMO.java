package com.live.paie.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;

@Entity
public class AMO extends LiveEntity {
    private float tauxAmoSalarial;
    private float tauxAmoPatronale;
    private float tauxParticition;

    public AMO() {
    }

    public AMO(float tauxAmoSalarial, float tauxAmoPatronale, float tauxParticition) {
        this.tauxAmoSalarial = tauxAmoSalarial;
        this.tauxAmoPatronale = tauxAmoPatronale;
        this.tauxParticition = tauxParticition;
    }

    public float getTauxAmoSalarial() {
        return tauxAmoSalarial;
    }

    public void setTauxAmoSalarial(float tauxAmoSalarial) {
        this.tauxAmoSalarial = tauxAmoSalarial;
    }

    public float getTauxAmoPatronale() {
        return tauxAmoPatronale;
    }

    public void setTauxAmoPatronale(float tauxAmoPatronale) {
        this.tauxAmoPatronale = tauxAmoPatronale;
    }

    public float getTauxParticition() {
        return tauxParticition;
    }

    public void setTauxParticition(float tauxParticition) {
        this.tauxParticition = tauxParticition;
    }
}
