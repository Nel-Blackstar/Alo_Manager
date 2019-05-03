package com.live.paie.entities;

import com.live.common.entities.LiveEntity;
import com.live.core.entities.Personnel;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Banque extends LiveEntity {
    private String nom;
    private String adresse;
    private int fax;
    private String telephone;
    @OneToOne(optional = true, targetEntity = Personnel.class)
    private Personnel personnel;

    public Banque() {
    }

    public Banque(String nom, String adresse, int fax, String telephone) {
        this.nom = nom;
        this.adresse = adresse;
        this.fax = fax;
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getFax() {
        return fax;
    }

    public void setFax(int fax) {
        this.fax = fax;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
}
