package net.j33r.flightcontrol.domain.flight;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@link FlightActionException} is an exception used when a flight action
 * cannot execute.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FlightActionException extends FlightException {

    private static final long serialVersionUID = -2610751441863621556L;

    public FlightActionException(final String message) {
        super(message);
    }

}
