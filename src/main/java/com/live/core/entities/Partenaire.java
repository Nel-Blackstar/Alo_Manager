package com.live.core.entities;

import com.live.common.entities.LiveEntity;
import com.live.paie.entities.Contrat;
import com.live.rh.entities.Offre;
import com.live.rh.entities.Prevision;
import com.live.rh.entities.RendezVous;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
public class Partenaire extends LiveEntity {
	@NotNull
    private String nom;
    private String prenom;
    @NotNull
    private String email;
    @NotNull
    private String telephone;
    @NotNull
    private String numero_cni;
    @NotNull
    private Character sexe;
    private String adresse;

    @Temporal(TemporalType.DATE)
    private Date date_naissance;
    private String lieu_naissance;
    private String photo;

    @OneToMany(targetEntity = Contrat.class)
    private List<Contrat> contrats;

    @OneToMany(targetEntity = Prevision.class)
    private List<Prevision> previsions;
    
    @ManyToMany(targetEntity = RendezVous.class)
    private List<RendezVous> rendezVous;

    @OneToMany(targetEntity = Offre.class)
    private List<Offre> offres;

    public Partenaire() {
    }

    public Partenaire(String nom, String prenom, String email, String telephone, String numero_cni, Character sexe, String adresse,
                      Date date_naissance, String lieu_naissance, String photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.numero_cni = numero_cni;
        this.sexe = sexe;
        this.adresse = adresse;
        this.date_naissance = date_naissance;
        this.lieu_naissance = lieu_naissance;
        this.photo = photo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNumero_cni() {
        return numero_cni;
    }

    public void setNumero_cni(String numero_cni) {
        this.numero_cni = numero_cni;
    }

    public Character getSexe() {
        return sexe;
    }

    public void setSexe(Character sexe) {
        this.sexe = sexe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getLieu_naissance() {
        return lieu_naissance;
    }

    public void setLieu_naissance(String lieu_naissance) {
        this.lieu_naissance = lieu_naissance;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }

    public List<Prevision> getPrevisions() {
        return previsions;
    }

    public void setPrevisions(List<Prevision> previsions) {
        this.previsions = previsions;
    }

    public List<RendezVous> getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(List<RendezVous> rendezVous) {
        this.rendezVous = rendezVous;
    }

    public List<Offre> getOffres() {
        return offres;
    }

    public void setOffres(List<Offre> offres) {
        this.offres = offres;
    }
}
