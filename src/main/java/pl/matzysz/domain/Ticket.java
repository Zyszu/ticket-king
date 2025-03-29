package pl.matzysz.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Flight flight;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;


    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }

}
