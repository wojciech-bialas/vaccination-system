package us.edu.pl.user.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.edu.pl.vaccination.Vaccination;
import us.edu.pl.vaccination.VaccinationReservation;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Long peselNumber;

    private String login;

    private String password;

    private String email;

    private String phoneNumber;

    private String personalIdNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "peselNumber"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id", cascade = CascadeType.ALL)
    private Set<Vaccination> vaccinations;

}
