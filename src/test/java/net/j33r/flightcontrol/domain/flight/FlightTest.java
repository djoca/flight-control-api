package net.j33r.flightcontrol.domain.flight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import net.j33r.flightcontrol.domain.aircraft.Aircraft;
import net.j33r.flightcontrol.domain.airport.Airport;
import net.j33r.flightcontrol.domain.airport.City;
import net.j33r.flightcontrol.domain.pilot.Pilot;

public class FlightTest {

    final Airport originAirport;

    final Airport destinationAirport;

    final Pilot pilot;

    final Aircraft aircraft;

    private final Flight flight;

    private final Flight scheduledFlight;

    public FlightTest() {
        final City originCity = new City((long) 1, "São Paulo");
        originAirport = new Airport((long) 1, "GRU", "Aeroporto de Guarulhos", originCity);

        final City destinationCity = new City((long) 1, "Paris");
        destinationAirport = new Airport((long) 1, "CDG", "Aéroport Charles de Gaule", destinationCity);

        pilot = new Pilot((long) 1, "Jack Black");

        aircraft = new Aircraft((long) 1, "Airbus", "A330", "PP-FTS", (short) 230, 2830, 230);

        flight = new Flight((long) 1, (short) 4152, "TAM", aircraft, pilot, originAirport, destinationAirport,
                FlightStatus.LANDED, new FlightDateTime(2017, 8, 28, 14, 13), new FlightDateTime(2017, 8, 28, 15, 20),
                new FlightDateTime(2017, 8, 28, 20, 10));

        scheduledFlight = new Flight((long) 1, (short) 4152, "TAM", aircraft, pilot, originAirport, destinationAirport,
                FlightStatus.ON_TIME, new FlightDateTime(2017, 8, 28, 14, 13), null, null);
    }

    @Test
    public void testDepartureTime() {
        final FlightDateTime departure = new FlightDateTime(2017, 8, 28, 15, 20);
        assertEquals(departure, flight.getDepartureTime());

        final FlightDateTime scheduledDeparture = new FlightDateTime(2017, 8, 28, 14, 13);
        assertEquals(scheduledDeparture, scheduledFlight.getDepartureTime());
    }

    @Test
    public void testFormattedDepartureTime() {
        assertEquals("28/08/2017 15:20", flight.getFormattedDepartureTime());
    }

    @Test
    public void testFormattedArrivalTime() {
        assertEquals("28/08/2017 20:10", flight.getFormattedArrivalTime());
    }

    @Test
    public void testAirports() {
        assertEquals("GRU", flight.getOriginAirportIataCode());
        assertEquals("CDG", flight.getDestinationAirportIataCode());
    }

    @Test
    public void testFlightStatus() {
        assertEquals("LANDED", flight.getStatusString());
    }

    @Test
    public void failedFlightCreation() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        final String tomorrow = LocalDateTime.now().plusDays(1).format(formatter);
        final FlightDateTime departureTime = FlightDateTime.parse(tomorrow);

        final Short flightNumber = 152;

        assertFlight(null, "Azul", aircraft, pilot, originAirport, destinationAirport, departureTime);
        assertFlight(flightNumber, null, aircraft, pilot, originAirport, destinationAirport, departureTime);
        assertFlight(flightNumber, "Azul", null, pilot, originAirport, destinationAirport, departureTime);
        assertFlight(flightNumber, "Azul", aircraft, null, originAirport, destinationAirport, departureTime);
        assertFlight(flightNumber, "Azul", aircraft, pilot, null, destinationAirport, departureTime);
        assertFlight(flightNumber, "Azul", aircraft, pilot, originAirport, null, departureTime);
        assertFlight(flightNumber, "Azul", aircraft, pilot, originAirport, destinationAirport, null);

        assertFlight(flightNumber, "Azul", aircraft, pilot, originAirport, originAirport, departureTime);

        final String yesterday = LocalDateTime.now().minusDays(1).format(formatter);

        assertFlight(flightNumber, "Azul", aircraft, pilot, originAirport, destinationAirport,
                FlightDateTime.parse(yesterday));
    }

    private void assertFlight(final Short number, final String companyName, final Aircraft aircraft, final Pilot pilot,
            final Airport origin, final Airport destination, final FlightDateTime departureTime) {
        try {
            new Flight(number, companyName, aircraft, pilot, origin, destination, departureTime);
            fail("Should throws FlightCreationException");
        } catch (final FlightCreationException e) {
            // Expected exception
        }
    }

}
