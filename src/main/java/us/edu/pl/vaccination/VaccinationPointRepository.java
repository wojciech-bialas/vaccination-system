package us.edu.pl.vaccination;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationPointRepository extends JpaRepository<VaccinationPoint, Long> {
}
