package com.live.paie.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.List;

@Entity
public class Profession extends LiveEntity {
    String nom;

    private String libeller;

    @OneToOne(targetEntity = CNPS.class)
    private CNPS cnps;

    public Profession() {
    }

    public Profession(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLibeller() {
        return libeller;
    }

    public void setLibeller(String libeller) {
        this.libeller = libeller;
    }

	public CNPS getCnps() {
		return cnps;
	}

	public void setCnps(CNPS cnps) {
		this.cnps = cnps;
	}
}
