package net.j33r.flightcontrol.restapi.aircraft;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.j33r.flightcontrol.domain.FlightControlApplicationService;
import net.j33r.flightcontrol.domain.aircraft.Aircraft;

/**
 * Aircraft API Rest controller.
 */
@CrossOrigin
@RestController
@AllArgsConstructor
public class AircraftAPIController {

    private final FlightControlApplicationService applicationService;

    /**
     * Return a JSON String containing a list of aircrafts.
     *
     * @return a JSON String with aircrafts information.
     */
    @GetMapping(value = "/aircrafts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AircraftListWrapper aircrafts() {
        final List<Aircraft> aircrafts = applicationService.retrieveAircrafts();
        return new AircraftListWrapper(aircrafts);
    }

}
