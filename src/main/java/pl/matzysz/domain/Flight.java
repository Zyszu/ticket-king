package pl.matzysz.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

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
    private long availableSeats;

    @NotNull
    private Timestamp departure;

    @NotNull
    private String airfieldFrom;

    @NotNull
    private String airfieldTo;

    @NotNull
    private float pricePerTicket;

}
