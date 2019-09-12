package com.live.moniteur.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class Dossier extends LiveEntity {
	@OneToOne
	private Inscription inscrit;

    private boolean photos;

    private boolean photocopieCni;

    private boolean certificatMedical;

    private  String codeRecipicer;

    private String dataExpiration;



    public Dossier() {
    }

    public Dossier(Inscription inscrit, boolean photos, boolean photocopieCni, boolean certificatMedical) {
        this.inscrit = inscrit;
        this.photos = photos;
        this.photocopieCni = photocopieCni;
        this.certificatMedical = certificatMedical;
    }

    public Inscription getInscrit() {
        return inscrit;
    }

    public void setInscrit(Inscription inscrit) {
        this.inscrit = inscrit;
    }

    public boolean isPhotos() {
        return photos;
    }

    public void setPhotos(boolean photos) {
        this.photos = photos;
    }

    public boolean isPhotocopieCni() {
        return photocopieCni;
    }

    public void setPhotocopieCni(boolean photocopieCni) {
        this.photocopieCni = photocopieCni;
    }

    public boolean isCertificatMedical() {
        return certificatMedical;
    }

    public void setCertificatMedical(boolean certificatMedical) {
        this.certificatMedical = certificatMedical;
    }

    public String getCodeRecipicer() {
        return codeRecipicer;
    }

    public void setCodeRecipicer(String codeRecipicer) {
        this.codeRecipicer = codeRecipicer;
    }

    public String getDataExpiration() {
        return dataExpiration;
    }

    public void setDataExpiration(String dataExpiration) {
        this.dataExpiration = dataExpiration;
    }
}
