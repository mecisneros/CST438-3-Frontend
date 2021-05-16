package com.example.CST4383Frontend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class City {
    @Id
    @NotNull
    private long id;

    @NotNull
    private String name;

    @NotNull
    @Size(min = 3, max = 3)
    private String countrycode;

    @NotNull
    private String district;

    @NotNull
    private int population;

    public City(long id, String name, String countrycode, String district, int population) {
        this.id = id;
        this.name = name;
        this.countrycode = countrycode;
        this.district = district;
        this.population = population;
    }

    public City() {
    }

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode='" + countrycode + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }

}
