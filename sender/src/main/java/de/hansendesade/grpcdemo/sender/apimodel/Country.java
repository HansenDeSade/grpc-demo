package de.hansendesade.grpcdemo.sender.apimodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    private Long id;
    private String name;
    private int factor;

    public Country() {

    }

    public Country(final Long id, final String name, final int factor) {
        this.id = id;
        this.name = name;
        this.factor = factor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{id=" + getId() + ", name='" + getName() + "'}";
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }
}
