package net.j33r.flightcontrol.domain.flight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.j33r.flightcontrol.config.SpringContextTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringContextTestConfiguration.class })
@ActiveProfiles("test")
public class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @Autowired
    private DatabasePopulator databasePopulator;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setup() throws Exception {
        databasePopulator.populate(dataSource.getConnection());
    }

    @Test
    public void createFlight() {
        final int numberOfFlights = flightService.retrieveFlights().size();

        final City originCity = new City(1L, "São Paulo");
        final Airport originAirport = new Airport(1L, "GRU", "Aeroporto de Guarulhos", originCity);

        final City destinationCity = new City(1L, "Paris");
        final Airport destinationAirport = new Airport(1L, "CDG", "Aéroport Charles de Gaule", destinationCity);

        final Pilot pilot = new Pilot(1L, "Jack Black");

        final Aircraft aircraft = new Aircraft(1L, "Airbus", "A330", "PP-FTS", (short) 230, 2830, 230);

        final FlightDateTime scheduledTime = FlightDateTime.parse("13/12/2018 13:20");

        final Flight flight = flightService.createFlight((short) 308, "Azul", aircraft, pilot, originAirport,
                destinationAirport, scheduledTime);

        assertNotNull(flight.getId());
        assertEquals(new Short((short) 308), flight.getNumber());
        assertEquals("Azul", flight.getCompanyName());
        assertEquals(FlightStatus.ON_TIME, flight.getStatus());
        assertEquals("Airbus", flight.getAircraftManufacturer());
        assertEquals("Jack Black", flight.getPilotName());
        assertNotNull(flight.getDepartureTime());
        assertEquals("13/12/2018 13:20", flight.getDepartureTime().getFormattedDateTime());
        assertEquals("GRU", flight.getOriginAirportIataCode());
        assertEquals("CDG", flight.getDestinationAirportIataCode());
        assertNull(flight.getArrivalTime());

        final int currentNumberOfFlights = flightService.retrieveFlights().size();

        assertEquals(numberOfFlights + 1, currentNumberOfFlights);
    }

    @Test
    public void delay() throws Exception {
        final Long id = 3L;

        final Flight flight = flightService.retrieveFlight(id);
        assertEquals(FlightStatus.ON_TIME, flight.getStatus());

        final Flight flyingFlight = flightService.delayFlight(id);
        assertEquals(FlightStatus.DELAYED, flyingFlight.getStatus());
        assertEquals(flight.getDepartureTime(), flyingFlight.getDepartureTime());
    }

    @Test
    public void takeOff() throws Exception {
        final Long id = 3L;

        final Flight flight = flightService.retrieveFlight(id);
        assertEquals(FlightStatus.ON_TIME, flight.getStatus());

        final Flight flyingFlight = flightService.takeOffFlight(id);
        assertEquals(FlightStatus.FLYING, flyingFlight.getStatus());
        assertNotEquals(flight.getDepartureTime(), flyingFlight.getDepartureTime());
    }

    @Test
    public void land() throws Exception {
        final Long id = 1L;

        final Flight flight = flightService.retrieveFlight(id);
        assertEquals(FlightStatus.FLYING, flight.getStatus());
        assertNull(flight.getArrivalTime());

        final Flight landedFlight = flightService.landFlight(id);
        assertEquals(FlightStatus.LANDED, landedFlight.getStatus());
        assertNotNull(landedFlight.getArrivalTime());
    }
}
