package net.j33r.flightcontrol.restapi;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Flight Control API Rest controller
 */
@RestController
public class FlightControlAPIController {

    /**
     * Return a JSON String containing flight informations.
     *
     * @return a JSON String with flight informations.
     */
    @RequestMapping(value = "/flights", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String flights() {
        final String jsonFormat = "{id: %s, flightNumber: '%s', companyName: '%s', origin: '%s', destination: '%s', flightStatus: '%s'}";

        final StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(String.format(jsonFormat, 1, 123, "Azul", "SJK", "BSB", "FLYING"));
        builder.append(",");
        builder.append(String.format(jsonFormat, 2, 300, "Gol", "GRU", "POA", "LANDED"));
        builder.append("]");

        return builder.toString();
    }
}
