package net.j33r.flightcontrol.restapi.airport;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.j33r.flightcontrol.domain.FlightControlApplicationService;
import net.j33r.flightcontrol.domain.airport.Airport;

/**
 * Aircraft API Rest controller.
 */
@CrossOrigin
@RestController
@AllArgsConstructor
public class AirportAPIController {

    private final FlightControlApplicationService applicationService;

    /**
     * Return a JSON String containing a list of airports.
     *
     * @return a JSON String with airports information.
     */
    @GetMapping(value = "/airports", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AirportListWrapper airports() {
        final List<Airport> airports = applicationService.retrieveAirports();
        return new AirportListWrapper(airports);
    }

}
