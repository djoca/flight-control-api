package net.j33r.flightcontrol.domain.aircraft;

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
public class AircraftServiceTest {

    @Autowired
    private AircraftService aircraftService;

    @Test
    public void retrieveAircrafts() throws Exception {
        final List<Aircraft> aircrafts = aircraftService.retrieveAircrafts();

        assertAircraft(aircrafts.get(0), 1L, "Embraer", "175", "PT-CAS", (short) 70, 5986, 3445);
        assertAircraft(aircrafts.get(1), 2L, "Boeing", "737", "PP-GOL", (short) 110, 15234, 7862);
    }

    @Test
    public void retrieveAircraft() throws Exception {
        final Aircraft aircraft = aircraftService.retrieveAircraft(1L);

        assertAircraft(aircraft, 1L, "Embraer", "175", "PT-CAS", (short) 70, 5986, 3445);
    }

    @Test
    public void retrieveNonExistentAircraft() throws Exception {
        try {
            aircraftService.retrieveAircraft(43L);
            fail("Should throw AircraftException");
        } catch (final AircraftException e) {
            assertEquals("Aircraft 43 not found", e.getMessage());
        }
    }

    private void assertAircraft(final Aircraft aircraft, final Long id, final String manufacturer, final String model,
            final String registry, final Short numberOfSeats, final Integer flightHours, final Integer flightCycles) {
        assertEquals(id, aircraft.getId());
        assertEquals(manufacturer, aircraft.getManufacturer());
        assertEquals(model, aircraft.getModel());
        assertEquals(registry, aircraft.getRegistry());
        assertEquals(numberOfSeats, aircraft.getNumberSeats());
        assertEquals(flightHours, aircraft.getFlightHours());
        assertEquals(flightCycles, aircraft.getFlightCycles());
    }

}
