package com.live.moniteur.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Cours extends LiveEntity {
	@NotNull
    private String module;
	@NotNull
    private String libelle;
    @NotNull
    private String type;

    @OneToMany(targetEntity = Chapitre.class)
    private List<Chapitre> chapitres;
    
    @ManyToOne
    @JoinColumn(name = "id_formation")
    private SessionFormation formation;

    public Cours() {
    }

    

    public Cours(String module, String libelle, String type) {
		this.module = module;
		this.libelle = libelle;
		this.type = type;
	}



	public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Chapitre> getChapitres() {
        return chapitres;
    }

    public void setChapitres(List<Chapitre> chapitres) {
        this.chapitres = chapitres;
    }

	public SessionFormation getFormation() {
		return formation;
	}

	public void setFormation(SessionFormation formation) {
		this.formation = formation;
	}
    
}
