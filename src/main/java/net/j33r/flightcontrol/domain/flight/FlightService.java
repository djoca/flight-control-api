package net.j33r.flightcontrol.domain.flight;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.j33r.flightcontrol.domain.aircraft.Aircraft;
import net.j33r.flightcontrol.domain.airport.Airport;
import net.j33r.flightcontrol.domain.pilot.Pilot;

/**
 * The class {@link FlightService} is the service layer for the Flight domain.
 */
@Service
@AllArgsConstructor
public class FlightService {

    private final FlightRepository repository;

    private final FlightActionFactory flightActionFactory;

    /**
     * Retrieve a {@link List} of {@link Flight} objects.
     *
     * @return a List of Flight objects
     */
    public List<Flight> retrieveFlights() {
        return Collections.unmodifiableList(repository.findAllFlights());
    }

    /**
     * Retrieve a {@link List} of {@link Flight} objects filtered by a search
     * string.
     */
    public List<Flight> retrieveFlights(String search) {
        return Collections.unmodifiableList(repository.searchFlights(search));
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
        final Flight flight = repository.findFlight(id);

        if (flight == null) {
            throw new FlightNotFoundException(id);
        }

        return flight;
    }

    /**
     * Creates a new flight
     *
     * @param number
     *            the number of the flight
     * @param companyName
     *            the company name operation the flight
     * @param aircraft
     *            the {@link Aircraft}
     * @param pilot
     *            the {@link Pilot}
     * @param origin
     *            the origin {@link Airport}
     * @param destination
     *            the origin {@link Airport}
     * @param scheduledTime
     *            the scheduled time
     * @return {@link Flight}
     */
    public Flight createFlight(final Short number, final String companyName, final Aircraft aircraft, final Pilot pilot,
            final Airport origin, final Airport destination, final FlightDateTime scheduledTime)
            throws FlightException {
        final Flight flight = new Flight(number, companyName, aircraft, pilot, origin, destination, scheduledTime);
        repository.save(flight);
        return flight;
    }

    /**
     * Change the status of a {@link Flight} as a result of a flight action.
     *
     * @param id
     *            the flight id
     * @param actionType
     *            the action type
     * @return a {@link Flight} with the status changed
     * @throws FlightException
     *             if the status could not be changed
     */
    public Flight changeStatus(final Long id, final FlightActionType actionType) throws FlightException {
        final FlightAction action = flightActionFactory.get(actionType);
        final Flight flight = retrieveFlight(id);
        action.execute(flight);
        repository.save(flight);
        return flight;
    }

}
