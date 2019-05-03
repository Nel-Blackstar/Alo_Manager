package com.live.paie.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;

@Entity
public class IGR extends LiveEntity {
    private float debutTranche;
    private float finTranche;
    private  float sommeADeduire;

    public IGR() {
    }

    public IGR(float debutTranche, float finTranche, float sommeADeduire) {
        this.debutTranche = debutTranche;
        this.finTranche = finTranche;
        this.sommeADeduire = sommeADeduire;
    }

    public float getDebutTranche() {
        return debutTranche;
    }

    public void setDebutTranche(float debutTranche) {
        this.debutTranche = debutTranche;
    }

    public float getFinTranche() {
        return finTranche;
    }

    public void setFinTranche(float finTranche) {
        this.finTranche = finTranche;
    }

    public float getSommeADeduire() {
        return sommeADeduire;
    }

    public void setSommeADeduire(float sommeADeduire) {
        this.sommeADeduire = sommeADeduire;
    }
}
