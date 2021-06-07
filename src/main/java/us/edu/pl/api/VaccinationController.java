package us.edu.pl.api;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import us.edu.pl.user.domain.UserRepository;
import us.edu.pl.vaccination.*;

import javax.transaction.Transactional;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/vaccination")
public class VaccinationController {

    private final VaccinationPointRepository vaccinationPointRepository;
    private final VaccinationReservationRepository vaccinationReservationRepository;
    private final UserRepository userRepository;
    private final VaccinationRepository vaccinationRepository;

    @GetMapping("/register/{vaccinationPointId}/{vaccinationReservationId}")
    @Transactional
    public String registerVaccination(@PathVariable("vaccinationPointId") Long vaccinationPointId,
                                      @PathVariable("vaccinationReservationId") Long vaccinationReservationId,
                                      Model model) {

        final VaccinationPoint vaccinationPoint = vaccinationPointRepository.findById(vaccinationPointId).get();
        final VaccinationReservation vaccinationReservation = vaccinationReservationRepository.findById(vaccinationReservationId).get();

        final String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        final us.edu.pl.user.domain.User user = userRepository.findByLogin(username);

        if (vaccinationRepository.findByUserAndVaccinationReservation(user, vaccinationReservation) != null) {
            return "index";
        }

        vaccinationRepository.save(
                new Vaccination(
                        null,
                        vaccinationPoint,
                        vaccinationReservation,
                        true,
                        false,
                        user
                )
        );

        vaccinationReservation.setReserved(true);
        vaccinationReservationRepository.save(vaccinationReservation);

        return "index";
    }

    @GetMapping("/findAll")
    @Transactional
    public String findAll(Model model) {
        final String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        final us.edu.pl.user.domain.User user = userRepository.findByLogin(username);

        final List<Vaccination> vaccinations = vaccinationRepository.findAllByUser(user);
        model.addAttribute("vaccinations", vaccinations);

        return "reservedVaccinations";
    }

    @GetMapping("/delete/{vaccinationId}/{vaccinationReservationId}")
    @Transactional
    public String delete(@PathVariable("vaccinationId") Long vaccinationId, @PathVariable("vaccinationReservationId") Long vaccinationReservationId,
                         Model model) {

        final VaccinationReservation vaccinationReservation =
                vaccinationReservationRepository.findById(vaccinationReservationId).get();
        vaccinationReservation.setReserved(false);

        vaccinationReservationRepository.save(vaccinationReservation);

        vaccinationRepository.deleteById(vaccinationId);

        final String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        final us.edu.pl.user.domain.User user = userRepository.findByLogin(username);

        final List<Vaccination> vaccinations = vaccinationRepository.findAllByUser(user);
        model.addAttribute("vaccinations", vaccinations);

        return "index";
    }
}
