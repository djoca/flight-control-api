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
     * Sets a flight as FLYING.
     *
     * @param id
     *            the flight id
     * @throws FlightNotFoundException
     *             if the id is not found
     * @return {@link Flight}
     */
    public Flight takeOffFlight(final Long id) throws FlightException {
        final Flight flight = retrieveFlight(id);
        flight.takeOff();
        repository.save(flight);
        return flight;
    }

    /**
     * Sets a flight as LANDED.
     *
     * @param id
     *            the flight id
     * @throws FlightNotFoundException
     *             if the id is not found
     * @return {@link Flight}
     */
    public Flight landFlight(final Long id) throws FlightException {
        final Flight flight = retrieveFlight(id);
        flight.land();
        repository.save(flight);
        return flight;
    }

    /**
     * Sets a flight as DELAYED.
     *
     * @param id
     *            the flight id
     * @throws FlightNotFoundException
     *             if the id is not found
     * @return {@link Flight}
     */
    public Flight delayFlight(final Long id) throws FlightException {
        final Flight flight = retrieveFlight(id);
        flight.delay();
        repository.save(flight);
        return flight;
    }

}
