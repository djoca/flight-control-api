package net.j33r.flightcontrol.domain.flight;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

/**
 * The {@link FlightCreationException} is an exception used when there is an
 * error in a flight creation, mostly during parameter validation.
 */
@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FlightCreationException extends FlightException {

    private static final long serialVersionUID = -8180772958263896967L;

    public FlightCreationException(final String message) {
        super(message);
    }

}
