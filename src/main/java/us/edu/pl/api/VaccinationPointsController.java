package us.edu.pl.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import us.edu.pl.vaccination.VaccinationPoint;
import us.edu.pl.vaccination.VaccinationPointRepository;
import us.edu.pl.vaccination.VaccinationReservation;
import us.edu.pl.vaccination.VaccinationReservationRepository;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/vaccination-points")
public class VaccinationPointsController {

    private final VaccinationPointRepository vaccinationPointRepository;
    private final VaccinationReservationRepository vaccinationReservationRepository;

    @GetMapping
    public String getVaccinationPoints(Model model) {
        model.addAttribute("vaccinationPoints", vaccinationPointRepository.findAll());
        return "vaccinationPoints";
    }

    @GetMapping("/available-dates/{vaccinationPointId}")
    public String getAvailableDates(@PathVariable("vaccinationPointId") Long vaccinationPointId,  Model model) {

        final List<VaccinationReservation> vaccinationReservations =
                vaccinationReservationRepository.findAllByReservedFalseAndVaccinationPointId(vaccinationPointId);

        model.addAttribute("vaccinationReservations", vaccinationReservations);

        return "chooseAvailableVaccinationDate";
    }
}
