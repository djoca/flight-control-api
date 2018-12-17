package net.j33r.flightcontrol.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.j33r.flightcontrol.config.SpringContextTestConfiguration;
import net.j33r.flightcontrol.domain.flight.Flight;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringContextTestConfiguration.class })
@ActiveProfiles("test")
public class FlightControlApplicationServiceTest {

    @Autowired
    private FlightControlApplicationService applicationService;

    @Test
    public void createFlight() throws Exception {
        final Short number = 8562;
        final String companyName = "Avianca";
        final Long aircraftId = 1L;
        final Long pilotId = 2L;
        final Long originAirportId = 3L;
        final Long destinationAirportId = 5L;
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        final String scheduledTime = LocalDateTime.now().plusDays(1).format(formatter);

        final Flight flight = applicationService.createFlight(number, companyName, aircraftId, pilotId, originAirportId,
                destinationAirportId, scheduledTime);

        assertNotNull(flight.getId());
        assertEquals("PT-CAS", flight.getAircraftRegistry());
        assertEquals("Bob Bobblehead", flight.getPilotName());
        assertEquals("CGH", flight.getOriginAirportIataCode());
        assertEquals("SDU", flight.getDestinationAirportIataCode());
        assertEquals(scheduledTime, flight.getFormattedDepartureTime());
        assertEquals("ON_TIME", flight.getStatusString());
    }
}
