package com.live.moniteur.entities;

import com.live.common.entities.CodeValue;
import com.live.common.entities.LiveEntity;
import com.live.core.entities.Apprenant;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Diplome extends LiveEntity {
    @OneToMany(targetEntity = CodeValue.class)
    private List<CodeValue> categorie;

    @ManyToOne
    private Apprenant apprenant;
    @Temporal(TemporalType.DATE)
    private Date dateDelivrance;
    private boolean statut;

    public Diplome() {
    }

    public Diplome(Date dateDelivrance, boolean statut) {
        this.dateDelivrance = dateDelivrance;
        this.statut = statut;
    }

    public Date getDateDelivrance() {
        return dateDelivrance;
    }

    public void setDateDelivrance(Date dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public Apprenant getApprenant() {
        return apprenant;
    }

    public void setApprenant(Apprenant apprenant) {
        this.apprenant = apprenant;
    }

    public List<CodeValue> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<CodeValue> categorie) {
        this.categorie = categorie;
    }
}
