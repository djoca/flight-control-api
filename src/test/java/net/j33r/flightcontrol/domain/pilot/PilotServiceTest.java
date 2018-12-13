package net.j33r.flightcontrol.domain.pilot;

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
public class PilotServiceTest {

    @Autowired
    private PilotService pilotService;

    @Test
    public void retrievePilot() throws Exception {
        final Pilot pilot = pilotService.retrievePilot(1L);

        assertEquals(new Long(1), pilot.getId());
        assertEquals("Jack Black", pilot.getName());
    }

}
