package us.edu.pl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import us.edu.pl.user.domain.Role;
import us.edu.pl.user.domain.RoleRepository;
import us.edu.pl.user.domain.User;
import us.edu.pl.user.domain.UserRepository;
import us.edu.pl.vaccination.VaccinationPoint;
import us.edu.pl.vaccination.VaccinationPointRepository;
import us.edu.pl.vaccination.VaccinationReservation;
import us.edu.pl.vaccination.VaccinationReservationRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;

@Component
@AllArgsConstructor
public class VaccinationPointsInitalization {

    private final VaccinationPointRepository vaccinationPointRepository;
    private final VaccinationReservationRepository vaccinationReservationRepository;

    @PostConstruct
    public void init() {
        final VaccinationPoint warsaw = new VaccinationPoint(
                null,
                "Warszawa",
                "41-006",
                "Marszalkowska 140",
                Collections.emptySet()
        );

        final VaccinationPoint katowice = new VaccinationPoint(
                null,
                "Katowice",
                "41-940",
                "Dworcowa 45",
                Collections.emptySet()
        );

        final VaccinationPoint krakow = new VaccinationPoint(
                null,
                "Krakow",
                "44-921",
                "Jana Pawla II 42",
                Collections.emptySet()
        );

        vaccinationPointRepository.save(warsaw);
        vaccinationPointRepository.save(katowice);
        vaccinationPointRepository.save(krakow);

        final VaccinationReservation vaccinationReservation1 = new VaccinationReservation(
                null,
                LocalDateTime.of(2021, 6, 21, 8, 30),
                LocalDateTime.of(2021, 6, 21, 9, 30),
                false,
                krakow
        );

        final VaccinationReservation vaccinationReservation2 = new VaccinationReservation(
                null,
                LocalDateTime.of(2021, 6, 21, 9, 30),
                LocalDateTime.of(2021, 6, 21, 10, 30),
                false,
                katowice
        );

        final VaccinationReservation vaccinationReservation3 = new VaccinationReservation(
                null,
                LocalDateTime.of(2021, 6, 21, 10, 30),
                LocalDateTime.of(2021, 6, 21, 11, 30),
                false,
                katowice
        );

        final VaccinationReservation vaccinationReservation4 = new VaccinationReservation(
                null,
                LocalDateTime.of(2021, 6, 22, 8, 30),
                LocalDateTime.of(2021, 6, 22, 9, 30),
                false,
                katowice
        );

        final VaccinationReservation vaccinationReservation5 = new VaccinationReservation(
                null,
                LocalDateTime.of(2021, 6, 22, 8, 30),
                LocalDateTime.of(2021, 6, 22, 9, 30),
                false,
                warsaw
        );

        final VaccinationReservation vaccinationReservation6 = new VaccinationReservation(
                null,
                LocalDateTime.of(2021, 6, 22, 8, 30),
                LocalDateTime.of(2021, 6, 22, 9, 30),
                false,
                warsaw
        );

        vaccinationReservationRepository.save(vaccinationReservation1);
        vaccinationReservationRepository.save(vaccinationReservation2);
        vaccinationReservationRepository.save(vaccinationReservation3);
        vaccinationReservationRepository.save(vaccinationReservation4);
        vaccinationReservationRepository.save(vaccinationReservation5);
        vaccinationReservationRepository.save(vaccinationReservation6);
    }

}
