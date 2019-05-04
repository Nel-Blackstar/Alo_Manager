package com.live.moniteur.entities;

import com.live.common.entities.CodeValue;
import com.live.common.entities.LiveEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class SessionFormation extends LiveEntity {
    private String delaiDossiers;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @OneToOne
    private CodeValue categoriePermis;
    private float fraisInscription;

    @OneToMany
    private Set<Inscription> inscriptions;

    @ManyToMany(targetEntity = Cours.class)
    @JoinTable(name = "formation_cours", joinColumns = @JoinColumn(name = "id_session"),
            inverseJoinColumns = @JoinColumn(name = "id_cours"))
    private List<Cours> formationCours;

    public SessionFormation() {
    }

    public SessionFormation(String delaiDossiers, Date dateDebut, Date dateFin, float fraisInscription) {
        this.delaiDossiers = delaiDossiers;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.fraisInscription = fraisInscription;
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

    public CodeValue getCategoriePermis() {
        return categoriePermis;
    }

    public void setCategoriePermis(CodeValue categoriePermis) {
        this.categoriePermis = categoriePermis;
    }

    public float getFraisInscription() {
        return fraisInscription;
    }

    public void setFraisInscription(float fraisInscription) {
        this.fraisInscription = fraisInscription;
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
}
