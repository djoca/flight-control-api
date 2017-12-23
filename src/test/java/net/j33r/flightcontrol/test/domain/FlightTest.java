package net.j33r.flightcontrol.test.domain;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import net.j33r.flightcontrol.domain.flight.Flight;

public class FlightTest {

    private Flight flight;

    @Before
    public void setup() {
        flight = new Flight((long) 1, (short) 4152, "TAM", "GRU", "CDG", "LANDED",
                LocalDateTime.of(2017, 8, 28, 14, 13), LocalDateTime.of(2016, 9, 13, 15, 20),
                LocalDateTime.of(2017, 11, 30, 20, 10));
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

}
