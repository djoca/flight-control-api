package net.j33r.flightcontrol.restapi.pilot;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.j33r.flightcontrol.domain.FlightControlApplicationService;
import net.j33r.flightcontrol.domain.pilot.Pilot;

/**
 * Pilot API Rest controller.
 */
@CrossOrigin
@RestController
@AllArgsConstructor
public class PilotAPIController {

    private final FlightControlApplicationService applicationService;

    /**
     * Return a JSON String containing a list of pilots.
     *
     * @return a JSON String with pilots information.
     */
    @GetMapping(value = "/pilots", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PilotListWrapper pilots() {
        final List<Pilot> pilots = applicationService.retrievePilots();
        return new PilotListWrapper(pilots);
    }

}
