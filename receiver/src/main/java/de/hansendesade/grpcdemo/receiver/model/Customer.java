package de.hansendesade.grpcdemo.receiver.model;

import io.hansendesade.countryAPI.CountryProviderGrpc;
import io.hansendesade.countryAPI.CountryProviderOuterClass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int countryId;
    private int customerValue;

    public Customer() {

    }

    public Customer(final Long id, final String name, final int countryId, final int customerValue) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
        this.customerValue = customerValue;
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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getCustomerValue() {
        return customerValue;
    }

    public void setCustomerValue(int customerValue) {
        this.customerValue = customerValue;
    }

    public CountryProviderOuterClass.CalculationRequest toCalculationRequest() {
        return CountryProviderOuterClass.CalculationRequest.newBuilder()
                .setCountryId(getCountryId())
                .setMultiplier(getCustomerValue())
                .build();
    }
}
