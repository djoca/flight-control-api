package net.j33r.flightcontrol.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.j33r.flightcontrol.domain.aircraft.Aircraft;
import net.j33r.flightcontrol.domain.aircraft.AircraftService;
import net.j33r.flightcontrol.domain.airport.Airport;
import net.j33r.flightcontrol.domain.airport.AirportService;
import net.j33r.flightcontrol.domain.flight.Flight;
import net.j33r.flightcontrol.domain.flight.FlightDateTime;
import net.j33r.flightcontrol.domain.flight.FlightException;
import net.j33r.flightcontrol.domain.flight.FlightNotFoundException;
import net.j33r.flightcontrol.domain.flight.FlightService;
import net.j33r.flightcontrol.domain.pilot.Pilot;
import net.j33r.flightcontrol.domain.pilot.PilotService;

/**
 * The class {@link FlightControlApplicationService} is the application layer
 * for the Flight Control API.
 */
@Service
@AllArgsConstructor
public class FlightControlApplicationService {

    private final FlightService flightService;

    private final AircraftService aircraftService;

    private final AirportService airportService;

    private final PilotService pilotService;

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
     * @throws FlightNotFoundException
     *             if the requested id is not found.
     */
    public Flight retrieveFlight(final Long id) throws FlightException {
        return flightService.retrieveFlight(id);
    }

    /**
     * Creates a new {@link Flight}
     *
     * @param number
     *            the flight number
     * @param companyName
     *            the company name operation this flight
     * @param aircraftId
     *            the {@link Aircraft} id
     * @param pilotId
     *            the {@link Pilot} id
     * @param originAirportId
     *            the origin {@link Airport} id
     * @param destinationAirportId
     *            the destination {@link Airport} id
     * @param dateTime
     *            the scheduled date and time
     * @return a {@link Flight} object
     */
    public Flight createFlight(final Short number, final String companyName, final Long aircraftId, final Long pilotId,
            final Long originAirportId, final Long destinationAirportId, final String departureTime)
            throws FlightControlException {

        final Aircraft aircraft = aircraftService.retrieveAircraft(aircraftId);
        final Pilot pilot = pilotService.retrievePilot(pilotId);
        final Airport origin = airportService.retrieveAirport(originAirportId);
        final Airport destination = airportService.retrieveAirport(destinationAirportId);
        final FlightDateTime scheduledTime = FlightDateTime.parse(departureTime);

        return flightService.createFlight(number, companyName, aircraft, pilot, origin, destination, scheduledTime);
    }
}
