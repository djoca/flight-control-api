package net.j33r.flightcontrol.restapi;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.j33r.flightcontrol.domain.FlightControlApplicationService;
import net.j33r.flightcontrol.domain.flight.Flight;
import net.j33r.flightcontrol.domain.flight.FlightException;
import net.j33r.flightcontrol.domain.flight.FlightNotFoundException;

/**
 * Flight Control API Rest controller.
 */
@CrossOrigin
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

    /**
     * Return a JSON String containing a flight details.
     *
     * @return a JSON String with flight information.
     * @throws FlightNotFoundException
     *             if the requested id is not found.
     */
    @RequestMapping(value = "/flights/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FlightWrapper flightDetails(@PathVariable final Long id) throws FlightException {
        final Flight flight = applicationService.retrieveFlight(id);
        return new FlightWrapper(flight);
    }
}
