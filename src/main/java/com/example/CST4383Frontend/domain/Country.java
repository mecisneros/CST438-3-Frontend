package com.example.CST4383Frontend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @NotNull
    private String code;

    @NotNull
    @Column(name = "Name")
    private String name;

    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Country() {
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Id
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
