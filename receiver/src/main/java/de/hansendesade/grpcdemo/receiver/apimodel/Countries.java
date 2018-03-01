package de.hansendesade.grpcdemo.receiver.apimodel;

import java.util.List;

public class Countries {

    private List<Country> countries;

    public Countries() {

    }

    public Countries(final List<Country> countries) {
        this.countries = countries;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
