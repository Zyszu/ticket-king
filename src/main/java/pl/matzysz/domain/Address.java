package pl.matzysz.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String country;

    @NotNull
    private String state;

    @NotNull
    private String city;

    @NotNull
    private String zipCode;

    @NotNull
    private String street;

    private String streetAdditional1;

    private String streetAdditional2;


    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getStreetAdditional1() { return streetAdditional1; }
    public void setStreetAdditional1(String streetAdditional1) { this.streetAdditional1 = streetAdditional1; }

    public String getStreetAdditional2() { return streetAdditional2; }
    public void setStreetAdditional2(String streetAdditional2) { this.streetAdditional2 = streetAdditional2; }
}
