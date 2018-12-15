package net.j33r.flightcontrol.domain.pilot;

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
public class PilotServiceTest {

    @Autowired
    private PilotService pilotService;

    @Test
    public void retrievePilots() throws Exception {
        final List<Pilot> pilots = pilotService.retrievePilots();

        assertEquals(2, pilots.size());
        assertPilot(pilots.get(0), 1L, "Jack Black");
        assertPilot(pilots.get(1), 2L, "Bob Bobblehead");
    }

    @Test
    public void retrievePilot() throws Exception {
        final Pilot pilot = pilotService.retrievePilot(1L);

        assertPilot(pilot, 1L, "Jack Black");
    }

    @Test
    public void retrieveNonExistentPilot() throws Exception {
        try {
            pilotService.retrievePilot(13L);
            fail("Should throw PilotException");
        } catch (final PilotException e) {
            assertEquals("Pilot 13 not found", e.getMessage());
        }
    }

    private void assertPilot(final Pilot pilot, final Long id, final String name) {
        assertEquals(id, pilot.getId());
        assertEquals(name, pilot.getName());
    }

}
