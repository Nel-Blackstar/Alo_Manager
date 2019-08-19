package com.live.core.entities;

import com.live.common.entities.Detail;
import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Live extends LiveEntity {

    private String nom;
    private String photo;
    private String localisation;
    private String slogan;
    private String adresse;
    private String telephone_1;
    private String telephone_2;
    private String email;
    private Double latitude;
    private Double longitude;
    private String site_web;
    private String numero_rc;
    private String boite_postale;
    private String fax;

    // List Detail
    @OneToMany(targetEntity = Detail.class)
    List<Detail> details;

    public Live() {
    }

    public Live(String nom, String localisation, String slogan, String adresse, String telephone_1, String telephone_2, String email,
                Double latitude, Double longitude, String site_web, String numero_rc, String boite_postale, String fax) {
        this.nom = nom;
        this.localisation = localisation;
        this.slogan = slogan;
        this.adresse = adresse;
        this.telephone_1 = telephone_1;
        this.telephone_2 = telephone_2;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.site_web = site_web;
        this.numero_rc = numero_rc;
        this.boite_postale = boite_postale;
        this.fax = fax;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getSite_web() {
        return site_web;
    }

    public void setSite_web(String site_web) {
        this.site_web = site_web;
    }

    public String getNumero_rc() {
        return numero_rc;
    }

    public void setNumero_rc(String numero_rc) {
        this.numero_rc = numero_rc;
    }

    public String getBoite_postale() {
        return boite_postale;
    }

    public void setBoite_postale(String boite_postale) {
        this.boite_postale = boite_postale;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
    
   
}
