package com.live.core.entities;

import com.live.common.entities.Detail;
import com.live.common.entities.LiveEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Personnel extends LiveEntity {
    private String nom;
    private String prenom;
    private String email;
    private String telephone_1;
    private String telephone_2;
    private String numero_cni;
    private Character sexe;

    @Temporal(TemporalType.DATE)
    private Date date_naissance;

    private String lieu_naissance;
    private String fonction;
    private String photo;

    @OneToOne(targetEntity = Users.class)
    private Users user;

    // List Detail
    @OneToMany(targetEntity = Detail.class)
    List<Detail> details;


    public Personnel() {
    }

    public Personnel(String nom, String prenom, String email, String telephone_1, String telephone_2, String numero_cni, Character sexe,
                     Date date_naissance, String lieu_naissance, String fonction) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone_1 = telephone_1;
        this.telephone_2 = telephone_2;
        this.numero_cni = numero_cni;
        this.sexe = sexe;
        this.date_naissance = date_naissance;
        this.lieu_naissance = lieu_naissance;
        this.fonction = fonction;
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

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }
}
