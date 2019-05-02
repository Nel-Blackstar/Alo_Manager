package com.live.paie.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;

@Entity
public class Anciennete extends LiveEntity {
    private float debutTranche;
    private float finTranche;
    private float tauxAnciennete;

    public Anciennete() {
    }

    public Anciennete(float debutTranche, float finTranche, float tauxAnciennete) {
        this.debutTranche = debutTranche;
        this.finTranche = finTranche;
        this.tauxAnciennete = tauxAnciennete;
    }

    public float getFinTranche() {
        return finTranche;
    }

    public void setFinTranche(float finTranche) {
        this.finTranche = finTranche;
    }

    public float getTauxAnciennete() {
        return tauxAnciennete;
    }

    public void setTauxAnciennete(float tauxAnciennete) {
        this.tauxAnciennete = tauxAnciennete;
    }

    public float getDebutTranche() {
        return debutTranche;
    }

    public void setDebutTranche(float debutTranche) {
        this.debutTranche = debutTranche;
    }
}
