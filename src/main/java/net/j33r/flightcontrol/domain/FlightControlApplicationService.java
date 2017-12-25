package net.j33r.flightcontrol.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.j33r.flightcontrol.domain.flight.Flight;
import net.j33r.flightcontrol.domain.flight.FlightService;

/**
 * The class {@link FlightControlApplicationService} is the application layer
 * for the Flight Control API.
 */
@Service
@AllArgsConstructor
public class FlightControlApplicationService {

    private final FlightService flightService;

    /**
     * Retrieve a {@link List} of {@link Flight} objects.
     *
     * @return a List of Flight objects
     */
    public List<Flight> retrieveFlights() {
        return flightService.retrieveFlights();
    }

    /**
     * Retrieve a Flight by its identifier
     *
     * @param id
     *            the flight identified
     * @return a Flight
     */
    public Flight retrieveFlight(Long id) {
        return flightService.retrieveFlight(id);
    }

}
