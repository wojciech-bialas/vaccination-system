package us.edu.pl.vaccination;

import org.springframework.data.jpa.repository.JpaRepository;
import us.edu.pl.user.domain.User;

import java.util.List;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    List<Vaccination> findAllByUser(User user);

    Vaccination findByUserAndVaccinationReservation(User user, VaccinationReservation vaccinationReservation);
}
