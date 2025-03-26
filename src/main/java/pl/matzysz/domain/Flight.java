package pl.matzysz.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.security.Timestamp;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Aircraft aircraft;

    @NotNull
    @Max(100)
    @Min(1)
    private int availableSeats;

    @NotNull
    private Timestamp departure;

    @NotNull
    private String airfieldFrom;

    @NotNull
    private String airfieldTo;

    @NotNull
    private float pricePerTicket;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Aircraft getAircraft() { return aircraft; }
    public void setAircraft(Aircraft aircraft) { this.aircraft = aircraft; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public Timestamp getDeparture() { return departure; }
    public void setDeparture(Timestamp departure) { this.departure = departure; }

    public String getAirfieldFrom() { return airfieldFrom; }
    public void setAirfieldFrom(String airfieldFrom) { this.airfieldFrom = airfieldFrom; }

    public String getAirfieldTo() { return airfieldTo; }
    public void setAirfieldTo(String airfieldTo) { this.airfieldTo = airfieldTo; }

    public float getPricePerTicket() { return pricePerTicket; }
    public void setPricePerTicket(float pricePerTicket) { this.pricePerTicket = pricePerTicket; }

}
