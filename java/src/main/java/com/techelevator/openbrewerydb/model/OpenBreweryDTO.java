package com.techelevator.openbrewerydb.model;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class OpenBreweryDTO {

    private String id;
    private String name;
    @JsonProperty("brewery_type")
    private String breweryType;
    @JsonProperty("address_1")
    private String address1;
    @JsonProperty("address_2")
    private Object address2;
    @JsonProperty("address_3")
    private Object address3;
    private String city;
    @JsonProperty("state_province")
    private String stateProvince;
    @JsonProperty("postal_code")
    private String postalCode;
    private String country;
    private String latitude;
    private String longitude;
    private String phone;
    @JsonProperty("website_url")
    private String websiteUrl;
    private String state;
    private String street;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("brewery_type")
    public String getBreweryType() {
        return breweryType;
    }

    @JsonProperty("brewery_type")
    public void setBreweryType(String breweryType) {
        this.breweryType = breweryType;
    }

    @JsonProperty("address_1")
    public String getAddress1() {
        return address1;
    }

    @JsonProperty("address_1")
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @JsonProperty("address_2")
    public Object getAddress2() {
        return address2;
    }

    @JsonProperty("address_2")
    public void setAddress2(Object address2) {
        this.address2 = address2;
    }

    @JsonProperty("address_3")
    public Object getAddress3() {
        return address3;
    }

    @JsonProperty("address_3")
    public void setAddress3(Object address3) {
        this.address3 = address3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("state_province")
    public String getStateProvince() {
        return stateProvince;
    }

    @JsonProperty("state_province")
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    @JsonProperty("postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postal_code")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("website_url")
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    @JsonProperty("website_url")
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
