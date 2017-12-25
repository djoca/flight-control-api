package net.j33r.flightcontrol.domain.flight;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

/**
 * The {@link FlightNotFoundException} is an exception used when a specified
 * flight is not found in the database.
 */
@Getter
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends FlightException {

    private static final long serialVersionUID = 4046455506585217991L;

    /**
     * The requested flight identifier
     */
    private final Long flightId;

    /**
     * Creates a new {@link FlightNotFoundException} with the specified flight
     * identifier.
     *
     * @param the
     *            requested flight identifier
     */
    public FlightNotFoundException(final Long flightId) {
        super(String.format("Flight %s not found", flightId));
        this.flightId = flightId;
    }

}
