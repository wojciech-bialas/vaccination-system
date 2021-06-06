package us.edu.pl.vaccination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import us.edu.pl.user.domain.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime date;

    @OneToOne
    private VaccinationPoint vaccinationPoint;

    private boolean qualified;
    private boolean vaccinated;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_pesel")
    private User user;

}
