package pl.matzysz.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "personal_data")
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @NotNull
    private String firstName;

    private String secondName;

    @NotNull
    private String lastName;

}
