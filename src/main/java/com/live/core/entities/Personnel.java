package com.live.core.entities;

import com.live.common.entities.Detail;
import com.live.common.entities.LiveEntity;
import com.live.paie.entities.*;

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
    private String matricule;
    private String adresse;
    private String situationFamiliale;
    private String compteBancaire;
    private String numeroCNPS;

    @Temporal(TemporalType.DATE)
    private Date date_naissance;

    private String lieu_naissance;
    private String fonction;
    private String photo;

    @OneToOne(targetEntity = Users.class)
    private Users user;

    @OneToMany(targetEntity = Detail.class)
    List<Detail> details;

    @OneToOne(targetEntity = Contrat.class)
    private Contrat contrats;

    @OneToMany(targetEntity = Prets.class)
    private List<Prets> prets;

    @ManyToOne(optional = true,targetEntity = Banque.class)
    private Banque banque;

    @OneToMany(targetEntity = Credits.class)
    private List<Credits> credits;

    @OneToMany(targetEntity = Avances.class)
    private List<Avances> avances;

    @OneToMany(targetEntity = PrimesVariables.class)
    private List<PrimesVariables> primesVariables;

    @ManyToMany(targetEntity = PrimesFixes.class)
    private List<PrimesFixes> primesFixes;
    
    @OneToMany(targetEntity = Enfants.class)
    private List<Enfants> enfants;

    @OneToMany(targetEntity = BulletinPaie.class)
    private List<BulletinPaie> bulletinPaie;

    @ManyToOne(optional = true, targetEntity = Entreprise.class)
    private Entreprise entreprise;

    @OneToMany(targetEntity = Conge.class)
    private List<Conge> conge;
    
    public Personnel() {
    }

    public Personnel(String nom, String prenom, String email, String telephone_1, String telephone_2, String numero_cni, Character sexe,
                     String matricule, String adresse, String situationFamiliale,
                     Date date_naissance, String lieu_naissance, String fonction, String compteBancaire, String photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone_1 = telephone_1;
        this.telephone_2 = telephone_2;
        this.numero_cni = numero_cni;
        this.sexe = sexe;
        this.matricule = matricule;
        this.adresse = adresse;
        this.situationFamiliale = situationFamiliale;
        this.date_naissance = date_naissance;
        this.lieu_naissance = lieu_naissance;
        this.fonction = fonction;
        this.photo = photo;
        this.compteBancaire = compteBancaire;
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

    public String getSituationFamiliale() {
        return situationFamiliale;
    }

    public void setSituationFamiliale(String situationFamiliale) {
        this.situationFamiliale = situationFamiliale;
    }

    public String getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(String compteBancaire) {
        this.compteBancaire = compteBancaire;
    }

    public String getNumeroCNPS() {
        return numeroCNPS;
    }

    public void setNumeroCNPS(String numeroCNPS) {
        this.numeroCNPS = numeroCNPS;
    }

    public Contrat getContrats() {
        return contrats;
    }

    public void setContrats(Contrat contrats) {
        this.contrats = contrats;
    }

    public List<Prets> getPrets() {
        return prets;
    }

    public void setPrets(List<Prets> prets) {
        this.prets = prets;
    }

    public Banque getBanque() {
		return banque;
	}

	public void setBanque(Banque banque) {
		this.banque = banque;
	}

	public List<Credits> getCredits() {
        return credits;
    }

    public void setCredits(List<Credits> credits) {
        this.credits = credits;
    }

    public List<Avances> getAvances() {
        return avances;
    }

    public void setAvances(List<Avances> avances) {
        this.avances = avances;
    }

    public List<PrimesVariables> getPrimesVariables() {
        return primesVariables;
    }

    public void setPrimesVariables(List<PrimesVariables> primesVariables) {
        this.primesVariables = primesVariables;
    }

    public List<PrimesFixes> getPrimesFixes() {
        return primesFixes;
    }

    public void setPrimesFixes(List<PrimesFixes> primesFixes) {
        this.primesFixes = primesFixes;
    }

    public List<BulletinPaie> getBulletinPaie() {
        return bulletinPaie;
    }

    public void setBulletinPaie(List<BulletinPaie> bulletinPaie) {
        this.bulletinPaie = bulletinPaie;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public List<Conge> getConge() {
        return conge;
    }

    public void setConge(List<Conge> conge) {
        this.conge = conge;
    }

	public List<Enfants> getEnfants() {
		return enfants;
	}

	public void setEnfants(List<Enfants> enfants) {
		this.enfants = enfants;
	}
    
}
