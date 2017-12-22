package net.j33r.flightcontrol.restapi;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.j33r.flightcontrol.domain.FlightControlApplicationService;
import net.j33r.flightcontrol.domain.flight.Flight;

/**
 * Flight Control API Rest controller.
 */
@RestController
@AllArgsConstructor
public class FlightControlAPIController {

    private final FlightControlApplicationService applicationService;

    /**
     * Return a JSON String containing flight informations.
     *
     * @return a JSON String with flight informations.
     */
    @RequestMapping(value = "/flights", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FlightListWrapper flights() {
        final List<Flight> flights = applicationService.retrieveFlights();
        return new FlightListWrapper(flights);
    }
}
