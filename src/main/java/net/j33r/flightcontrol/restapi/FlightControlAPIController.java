package net.j33r.flightcontrol.restapi;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.j33r.flightcontrol.domain.FlightControlApplicationService;
import net.j33r.flightcontrol.domain.FlightControlException;
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
    @GetMapping(value = "/flights", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    @GetMapping(value = "/flights/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FlightWrapper flightDetails(@PathVariable final Long id) throws FlightException {
        final Flight flight = applicationService.retrieveFlight(id);
        return new FlightWrapper(flight);
    }

    /**
     * Creates a new flight
     *
     * @param flightWrapper
     *            an instance of a {@link FlightWrapper} deserialized from a
     *            json string
     * @throws FlightControlException
     *             if the flight could not be created
     */
    @PostMapping(value = "/flights", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createFlight(@RequestBody final FlightWrapper flightWrapper) throws FlightControlException {
        final Short number = flightWrapper.getNumber();
        final String companyName = flightWrapper.getCompanyName();
        final Long aircraftId = flightWrapper.getAircraftId();
        final Long pilotId = flightWrapper.getPilotId();
        final Long originId = flightWrapper.getOriginId();
        final Long destinationId = flightWrapper.getDestinationId();
        final String departureTime = flightWrapper.getDepartureTime();

        applicationService.createFlight(number, companyName, aircraftId, pilotId, originId, destinationId,
                departureTime);
    }
}
