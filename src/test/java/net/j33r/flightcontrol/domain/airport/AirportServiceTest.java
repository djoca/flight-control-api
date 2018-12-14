package net.j33r.flightcontrol.domain.airport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
    public void retrieveAirport() throws Exception {
        final Airport airport = airportService.retrieveAirport(1L);

        assertEquals(new Long(1), airport.getId());
        assertEquals("SJK", airport.getIataCode());
        assertEquals("Aeroporto de São José dos Campos", airport.getName());
        assertEquals("São José dos Campos", airport.getCityName());
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

}
