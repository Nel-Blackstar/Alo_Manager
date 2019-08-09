package com.live.rh.entities;

import com.live.common.entities.LiveEntity;
import com.live.core.entities.Partenaire;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
public class Offre extends LiveEntity {
	@DecimalMin("0")
    private Long stocksInitial;
	@DecimalMin("0")
	@NotNull
    private Long quantite;
	@NotNull
    private double pp;
	private double pvp;
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_fourniture")
    private Fournitures fournitures;

    @ManyToOne
    @JoinColumn(name = "id_partenaire")
    private Partenaire partenaire;

    @OneToMany
    @JoinColumn(name = "id_offre")
    private List<Details> details;

    public Offre() {
    }

    public Offre(Long quantite,Long stocksInitial, double pp,double pvp, Date date) {
        this.quantite = quantite;
        this.pp = pp;
        this.pvp = pvp;
        this.date = date;
        this.stocksInitial=stocksInitial;
    }

    public Long getQuantite() {
        return quantite;
    }

    public void setQuantite(Long quantite) {
        this.quantite = quantite;
    }
    
    
	public void setStocksInitial(Long stocksInitial) {
		this.stocksInitial = stocksInitial;
	}

	public Long getStocksInitial() {
		return stocksInitial;
	}

	public double getPp() {
        return pp;
    }

    public void setPp(double pp) {
        this.pp = pp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Fournitures getFournitures() {
        return fournitures;
    }

    public void setFournitures(Fournitures fournitures) {
        this.fournitures = fournitures;
    }

    public Partenaire getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

	public double getPvp() {
		return pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }
    
}
