package net.j33r.flightcontrol.restapi;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.j33r.flightcontrol.domain.FlightControlApplicationService;
import net.j33r.flightcontrol.domain.flight.Flight;

/**
 * Flight Control API Rest controller.
 */
@RestController
@AllArgsConstructor
public class FlightControlAPIController {

    private final FlightControlApplicationService applicationService;

    /**
     * Return a JSON String containing flight informations.
     *
     * @return a JSON String with flight informations.
     */
    @RequestMapping(value = "/flights", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String flights() {
        final String jsonFormat = "{id: %s, flightNumber: '%s', companyName: '%s', origin: '%s', destination: '%s', flightStatus: '%s', departureTime: '%s'}";

        final List<Flight> flights = applicationService.retrieveFlights();

        final StringBuilder builder = new StringBuilder();

        builder.append("[");
        boolean first = true;

        for (final Flight flight : flights) {
            if (!first) {
                builder.append(",");
            }
            first = false;

            builder.append(String.format(jsonFormat, flight.getId(), flight.getNumber(), flight.getCompanyName(),
                    flight.getOrigin(), flight.getDestination(), flight.getStatus(),
                    flight.getFormatedDepartureTime()));
        }

        builder.append("]");

        return builder.toString();
    }
}
