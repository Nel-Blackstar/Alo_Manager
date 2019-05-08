package com.live.moniteur.entities;

import com.live.common.entities.LiveEntity;
import com.live.rh.entities.Apprenant;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Inscription extends LiveEntity {

    private float paiement;
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_formation")
    private SessionFormation formation;

    @ManyToOne
    @JoinColumn(name = "id_apprenant")
    private Apprenant apprenant;

    public float getPaiement() {
        return paiement;
    }

    public void setPaiement(float paiement) {
        this.paiement = paiement;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SessionFormation getFormation() {
        return formation;
    }

    public void setFormation(SessionFormation formation) {
        this.formation = formation;
    }

    public Apprenant getApprenant() {
        return apprenant;
    }

    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant;
    }
}
