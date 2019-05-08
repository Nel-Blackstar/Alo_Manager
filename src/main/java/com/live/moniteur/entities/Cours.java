package com.live.moniteur.entities;

import com.live.common.entities.LiveEntity;
import com.live.core.entities.Users;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cours extends LiveEntity {
    private String module;
    private String libelle;
    private String type;

    @OneToMany(targetEntity = Chapitre.class)
    private List<Chapitre> chapitres;

    @ManyToMany(targetEntity = Users.class)
    @JoinTable(name="formation_cours", joinColumns = {
            @JoinColumn(name = "id_cours")}, inverseJoinColumns = {
            @JoinColumn(name = "id_session")})
    private List<SessionFormation> sessionFormations;

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

    public List<SessionFormation> getSessionFormations() {
        return sessionFormations;
    }

    public void setSessionFormations(List<SessionFormation> sessionFormations) {
        this.sessionFormations = sessionFormations;
    }
}
