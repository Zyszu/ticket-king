package pl.matzysz.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "aircrafts")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String model;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
}
