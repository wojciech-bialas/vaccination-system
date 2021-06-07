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
public class VaccinationReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime begin;

    private LocalDateTime end;

    private boolean reserved;

    @ManyToOne
    @JoinColumn(name="vaccination_point_id", nullable=false)
    private VaccinationPoint vaccinationPoint;

}
