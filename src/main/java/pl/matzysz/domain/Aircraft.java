package pl.matzysz.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "aircrafts")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 1, max = 50)
    private String model;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id") // owns the FK
    private Company company;


    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
}
