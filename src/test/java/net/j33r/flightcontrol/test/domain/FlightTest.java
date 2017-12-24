package net.j33r.flightcontrol.test.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import net.j33r.flightcontrol.domain.flight.Aircraft;
import net.j33r.flightcontrol.domain.flight.Airport;
import net.j33r.flightcontrol.domain.flight.City;
import net.j33r.flightcontrol.domain.flight.Flight;
import net.j33r.flightcontrol.domain.flight.FlightDateTime;
import net.j33r.flightcontrol.domain.flight.FlightStatus;
import net.j33r.flightcontrol.domain.flight.Pilot;

public class FlightTest {

    private Flight flight;

    @Before
    public void setup() {
        final City originCity = new City((long) 1, "São Paulo");
        final Airport originAirport = new Airport((long) 1, "GRU", "Aeroporto de Guarulhos", originCity);

        final City destinationCity = new City((long) 1, "Paris");
        final Airport destinationAirport = new Airport((long) 1, "CDG", "Aéroport Charles de Gaule", destinationCity);

        final Pilot pilot = new Pilot((long) 1, "Jack Black");

        final Aircraft aircraft = new Aircraft((long) 1, "Airbus", "A330", "PP-FTS", (short) 230, 2830, 230);

        flight = new Flight((long) 1, (short) 4152, "TAM", aircraft, pilot, originAirport, destinationAirport,
                FlightStatus.LANDED, new FlightDateTime(2017, 8, 28, 14, 13), new FlightDateTime(2016, 9, 13, 15, 20),
                new FlightDateTime(2017, 11, 30, 20, 10));
    }

    @Test
    public void testFormattedScheduledDepartureTime() {
        assertEquals("28/08/2017 14:13", flight.getFormattedScheduledDepartureTime());
    }

    @Test
    public void testFormattedDepartureTime() {
        assertEquals("13/09/2016 15:20", flight.getFormattedDepartureTime());
    }

    @Test
    public void testFormattedArrivalTime() {
        assertEquals("30/11/2017 20:10", flight.getFormattedArrivalTime());
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

}
