package net.j33r.flightcontrol.domain.airport;

import net.j33r.flightcontrol.domain.FlightControlException;

/**
 * The {@link AirportException} is the super class for the Airport domain
 * exceptions.
 */
public class AirportException extends FlightControlException {

    private static final long serialVersionUID = 8588827018455680461L;

    /**
     * {@inheritDoc}
     */
    public AirportException(final String message) {
        super(message);
    }
}
