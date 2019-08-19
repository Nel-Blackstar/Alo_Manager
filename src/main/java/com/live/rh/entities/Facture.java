package com.live.rh.entities;

import com.live.common.entities.LiveEntity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class Facture extends LiveEntity {

	private String acteur;
    private String description;
    @ManyToOne(targetEntity = Sortie.class)
    private Set<Sortie> sortie;

    public Facture() {
    }
    
	public Facture(String acteur, String description) {
		super();
		this.acteur = acteur;
		this.description = description;
	}
	
	
	public String getActeur() {
		return acteur;
	}

	public void setActeur(String acteur) {
		this.acteur = acteur;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Sortie> getSortie() {
		return sortie;
	}

	public void setSortie(Set<Sortie> sortie) {
		this.sortie = sortie;
	}
    
	
   
}
