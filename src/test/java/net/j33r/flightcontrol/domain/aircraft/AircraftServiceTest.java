package net.j33r.flightcontrol.domain.aircraft;

import static org.junit.Assert.assertEquals;

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
    public void retrieveAircraft() throws Exception {
        final Aircraft aircraft = aircraftService.retrieveAircraft(1L);

        assertEquals(new Long(1), aircraft.getId());
        assertEquals("Embraer", aircraft.getManufacturer());
        assertEquals("175", aircraft.getModel());
        assertEquals("PT-CAS", aircraft.getRegistry());
        assertEquals(new Short((short) 70), aircraft.getNumberSeats());
        assertEquals(new Integer(5986), aircraft.getFlightHours());
        assertEquals(new Integer(3445), aircraft.getFlightCycles());
    }
}
