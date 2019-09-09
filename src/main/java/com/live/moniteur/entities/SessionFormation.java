package com.live.moniteur.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class SessionFormation extends LiveEntity {

    private  String libeller;
	@NotNull
    private String delaiDossiers;
	@NotNull
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
	@NotNull
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @OneToMany
    private Set<Inscription> inscriptions;

    @OneToMany(targetEntity = Cours.class)
    private List<Cours> formationCours;

    public SessionFormation() { }

    public SessionFormation(String delaiDossiers, Date dateDebut, Date dateFin) {
        this.delaiDossiers = delaiDossiers;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public String getDelaiDossiers() {
        return delaiDossiers;
    }

    public void setDelaiDossiers(String delaiDossiers) {
        this.delaiDossiers = delaiDossiers;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Set<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(Set<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public List<Cours> getFormationCours() {
        return formationCours;
    }

    public void setFormationCours(List<Cours> formationCours) {
        this.formationCours = formationCours;
    }

    public String getLibeller() {
        return libeller;
    }

    public void setLibeller(String libeller) {
        this.libeller = libeller;
    }
}
