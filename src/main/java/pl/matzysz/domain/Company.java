package pl.matzysz.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private User owner;

    @NotNull
    private boolean verified;

    @NotNull
    private boolean active;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(unique = true)
    private String companyName;

    @NotNull
    @Size(min = 1, max = 50)
    private String nip;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Aircraft> aircraftList = new HashSet<>(0);

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Ticket> ticketList =  new HashSet<>(0);

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getNip() { return this.nip; }
    public void setNip(String nip) { this.nip = nip;  }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public Set<Aircraft> getAircraftList() { return aircraftList; }
    public void setAircraftList(Set<Aircraft> aircraftList) { this.aircraftList = aircraftList; }

    public Set<Ticket> getTicketList() { return ticketList; }
    public void setTicketList(Set<Ticket> ticketList) { this.ticketList = ticketList; }

}
