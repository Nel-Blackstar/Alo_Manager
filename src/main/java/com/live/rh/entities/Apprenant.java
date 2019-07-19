package com.live.rh.entities;

import com.live.common.entities.LiveEntity;
import com.live.moniteur.entities.Inscription;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.Set;

@Entity
public class Apprenant extends LiveEntity {
    private String nom;
    private String prenom;
    private String email;
    @NotNull
    private String telephone_1;
    private String telephone_2;
    @NotNull
    private String numero_cni;
    private Character sexe;
    private String matricule;
    private String adresse;
    private String typeApprenant;

    @Temporal(TemporalType.DATE)
    private Date date_naissance;
    private String lieu_naissance;
    private String photo;

    @OneToMany
    private Set<Inscription> inscriptions;

    public Apprenant() {
    }

    public Apprenant(String nom, String prenom, String email, String telephone_1, String telephone_2,
                     String numero_cni, Character sexe, String matricule, String adresse, String typeApprenant,
                     Date date_naissance, String lieu_naissance, String photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone_1 = telephone_1;
        this.telephone_2 = telephone_2;
        this.numero_cni = numero_cni;
        this.sexe = sexe;
        this.matricule = matricule;
        this.adresse = adresse;
        this.typeApprenant = typeApprenant;
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

    public String getTelephone_1() {
        return telephone_1;
    }

    public void setTelephone_1(String telephone_1) {
        this.telephone_1 = telephone_1;
    }

    public String getTelephone_2() {
        return telephone_2;
    }

    public void setTelephone_2(String telephone_2) {
        this.telephone_2 = telephone_2;
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

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
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

    public String getTypeApprenant() {
        return typeApprenant;
    }

    public void setTypeApprenant(String typeApprenant) {
        this.typeApprenant = typeApprenant;
    }

    public Set<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(Set<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }
    
}
