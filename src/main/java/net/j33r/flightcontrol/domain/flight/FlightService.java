package net.j33r.flightcontrol.domain.flight;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * The class {@link FlightService} is the service layer for the Flight domain.
 */
@Service
@AllArgsConstructor
public class FlightService {

    private final FlightRepository repository;

    /**
     * Retrieve a {@link List} of {@link Flight} objects.
     *
     * @return a List of Flight objects
     */
    public List<Flight> retrieveFlights() {
        return Collections.unmodifiableList(repository.findAllFlights());
    }

    /**
     * Retrieve a Flight by its identifier
     *
     * @param id
     *            the flight identified
     * @return a Flight
     * @throws FlightNotFoundException
     *             if the requested id is not found.
     */
    public Flight retrieveFlight(final Long id) throws FlightException {
        final Flight flight = repository.findOne(id);

        if (flight == null) {
            throw new FlightNotFoundException(id);
        }

        return flight;
    }

}
