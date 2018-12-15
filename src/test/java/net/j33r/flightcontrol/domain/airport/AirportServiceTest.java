package net.j33r.flightcontrol.domain.airport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.j33r.flightcontrol.config.SpringContextTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringContextTestConfiguration.class })
@ActiveProfiles("test")
public class AirportServiceTest {

    @Autowired
    private AirportService airportService;

    @Test
    public void retrieveAirports() throws Exception {
        final List<Airport> airports = airportService.retrieveAirports();

        assertEquals(5, airports.size());
        assertAirport(airports.get(0), 1L, "SJK", "Aeroporto de São José dos Campos", 1L, "São José dos Campos");
        assertAirport(airports.get(1), 2L, "GRU", "Aeroporto Internacional Governador André Franco Montoro", 2L,
                "São Paulo");
        assertAirport(airports.get(2), 3L, "CGH", "Aeroporto de Congonhas", 2L, "São Paulo");
    }

    @Test
    public void retrieveAirport() throws Exception {
        final Airport airport = airportService.retrieveAirport(1L);

        assertAirport(airport, 1L, "SJK", "Aeroporto de São José dos Campos", 1L, "São José dos Campos");
    }

    @Test
    public void retrieveNonExistentAirport() throws Exception {
        try {
            airportService.retrieveAirport(10L);
            fail("Should throw AirportException");
        } catch (final AirportException e) {
            assertEquals("Airport 10 not found", e.getMessage());
        }
    }

    private void assertAirport(final Airport airport, final Long airportId, final String iataCode,
            final String airportName, final Long cityId, final String cityName) {
        assertEquals(airportId, airport.getId());
        assertEquals(iataCode, airport.getIataCode());
        assertEquals(airportName, airport.getName());
        assertEquals(cityId, airport.getCityId());
        assertEquals(cityName, airport.getCityName());
    }

}
