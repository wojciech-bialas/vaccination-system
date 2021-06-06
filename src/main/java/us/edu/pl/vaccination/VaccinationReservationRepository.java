package us.edu.pl.vaccination;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinationReservationRepository extends JpaRepository<VaccinationReservation, Long> {

    List<VaccinationReservation> findAllByReservedFalseAndVaccinationPointId(Long vaccinationPointId);
}
