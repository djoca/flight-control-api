package net.j33r.flightcontrol.domain.flight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import net.j33r.flightcontrol.domain.aircraft.Aircraft;
import net.j33r.flightcontrol.domain.airport.Airport;
import net.j33r.flightcontrol.domain.airport.City;
import net.j33r.flightcontrol.domain.pilot.Pilot;

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
    public void createFlight() throws Exception {
        final int numberOfFlights = flightService.retrieveFlights().size();

        final City originCity = new City(1L, "São Paulo");
        final Airport originAirport = new Airport(1L, "GRU", "Aeroporto de Guarulhos", originCity);

        final City destinationCity = new City(1L, "Paris");
        final Airport destinationAirport = new Airport(1L, "CDG", "Aéroport Charles de Gaule", destinationCity);

        final Pilot pilot = new Pilot(1L, "Jack Black");

        final Aircraft aircraft = new Aircraft(1L, "Airbus", "A330", "PP-FTS", (short) 230, 2830, 230);

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        final String tomorrow = LocalDateTime.now().plusDays(1).format(formatter);
        final FlightDateTime scheduledTime = FlightDateTime.parse(tomorrow);

        final Flight flight = flightService.createFlight((short) 308, "Azul", aircraft, pilot, originAirport,
                destinationAirport, scheduledTime);

        assertNotNull(flight.getId());
        assertEquals(new Short((short) 308), flight.getNumber());
        assertEquals("Azul", flight.getCompanyName());
        assertEquals(FlightStatus.ON_TIME, flight.getStatus());
        assertEquals("Airbus", flight.getAircraftManufacturer());
        assertEquals("Jack Black", flight.getPilotName());
        assertNotNull(flight.getDepartureTime());
        assertEquals(tomorrow, flight.getDepartureTime().getFormattedDateTime());
        assertEquals("GRU", flight.getOriginAirportIataCode());
        assertEquals("CDG", flight.getDestinationAirportIataCode());
        assertNull(flight.getArrivalTime());

        final int currentNumberOfFlights = flightService.retrieveFlights().size();

        assertEquals(numberOfFlights + 1, currentNumberOfFlights);
    }

    @Test
    public void retrieveNonExistentFlight() throws Exception {
        try {
            flightService.retrieveFlight(152L);
            fail("Should throw FlightNotFoundException");
        } catch (final FlightNotFoundException e) {
            assertEquals("Flight 152 not found", e.getMessage());
        }
    }

    @Test
    public void retrieveNullFlight() throws Exception {
        try {
            flightService.retrieveFlight(null);
            fail("Should throw FlightNotFoundException");
        } catch (final FlightNotFoundException e) {
            assertEquals("Flight null not found", e.getMessage());
        }
    }

    @Test
    public void delay() throws Exception {
        final Long id = 3L;

        final Flight flight = flightService.retrieveFlight(id);
        assertEquals(FlightStatus.ON_TIME, flight.getStatus());

        final Flight delayedFlight = flightService.changeStatus(id, FlightActionType.DELAY);
        assertEquals(FlightStatus.DELAYED, delayedFlight.getStatus());
        assertEquals(flight.getDepartureTime(), delayedFlight.getDepartureTime());
    }

    @Test
    public void takeOff() throws Exception {
        final Long id = 3L;

        final Flight flight = flightService.retrieveFlight(id);
        assertEquals(FlightStatus.ON_TIME, flight.getStatus());

        final Flight flyingFlight = flightService.changeStatus(id, FlightActionType.TAKE_OFF);
        assertEquals(FlightStatus.FLYING, flyingFlight.getStatus());
        assertNotEquals(flight.getDepartureTime(), flyingFlight.getDepartureTime());
    }

    @Test
    public void land() throws Exception {
        final Long id = 1L;

        final Flight flight = flightService.retrieveFlight(id);
        assertEquals(FlightStatus.FLYING, flight.getStatus());
        assertNull(flight.getArrivalTime());

        final Flight landedFlight = flightService.changeStatus(id, FlightActionType.LAND);
        assertEquals(FlightStatus.LANDED, landedFlight.getStatus());
        assertNotNull(landedFlight.getArrivalTime());
    }

    @Test
    public void incorrectFlightActions() throws Exception {
        assertIncorrectFlightAction(1L, FlightActionType.DELAY);
        assertIncorrectFlightAction(1L, FlightActionType.TAKE_OFF);
        assertIncorrectFlightAction(2L, FlightActionType.DELAY);
        assertIncorrectFlightAction(2L, FlightActionType.TAKE_OFF);
        assertIncorrectFlightAction(2L, FlightActionType.LAND);
        assertIncorrectFlightAction(3L, FlightActionType.LAND);
    }

    private void assertIncorrectFlightAction(final Long id, final FlightActionType actionType) throws Exception {
        try {
            flightService.changeStatus(id, actionType);
            fail("Should throw FlightException");
        } catch (final FlightException e) {
            // Expected exception
        }
    }
}
