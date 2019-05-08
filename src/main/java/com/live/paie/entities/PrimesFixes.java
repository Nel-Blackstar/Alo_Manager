package com.live.paie.entities;

import com.live.common.entities.LiveEntity;
import com.live.core.entities.Personnel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PrimesFixes extends LiveEntity {
    private String type;
    private  String code;
    private String nom;
    private float valeur;
    private boolean exoneree;
    @ManyToOne(optional = true, targetEntity = Personnel.class)
    private Personnel personnel;

    public PrimesFixes() {
    }

    public PrimesFixes(String type, String code, String nom, float valeur, boolean exoneree) {
        this.type = type;
        this.code = code;
        this.nom = nom;
        this.valeur = valeur;
        this.exoneree = exoneree;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public boolean isExoneree() {
        return exoneree;
    }

    public void setExoneree(boolean exoneree) {
        this.exoneree = exoneree;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
}
