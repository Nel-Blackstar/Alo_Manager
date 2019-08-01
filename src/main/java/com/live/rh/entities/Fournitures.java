package com.live.rh.entities;

import com.live.common.entities.LiveEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Fournitures extends LiveEntity {
	@NotNull
    private String type;
    private String description;

    public Fournitures() {
    }

    public Fournitures(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
