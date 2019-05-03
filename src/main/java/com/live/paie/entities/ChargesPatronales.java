package com.live.paie.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class ChargesPatronales extends LiveEntity {
    @Temporal(TemporalType.DATE)
    private Date date;
    private String type;

    public ChargesPatronales() {
    }

    public ChargesPatronales(Date date, String type) {
        this.date = date;
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
