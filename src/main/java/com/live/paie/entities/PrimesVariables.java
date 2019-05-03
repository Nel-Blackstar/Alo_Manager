package com.live.paie.entities;

import com.live.common.entities.LiveEntity;
import com.live.core.entities.Personnel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PrimesVariables extends LiveEntity {
    private String nom;
    private String code;
    private  float valeur;
    private String type;
    private boolean exoneree;
    @ManyToOne(optional = true, targetEntity = Personnel.class)
    private Personnel personnel;

    public PrimesVariables() {
    }

    public PrimesVariables(String nom, String code, float valeur, String type, boolean exoneree) {
        this.nom = nom;
        this.code = code;
        this.valeur = valeur;
        this.type = type;
        this.exoneree = exoneree;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isExoneree() {
        return exoneree;
    }

    public void setExoneree(boolean exoneree) {
        this.exoneree = exoneree;
    }
}
