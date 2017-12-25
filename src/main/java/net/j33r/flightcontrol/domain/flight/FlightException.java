package net.j33r.flightcontrol.domain.flight;

/**
 * The {@link FlightException} is the super class for the Flight domain
 * exceptions.
 */
public class FlightException extends Exception {

    private static final long serialVersionUID = -8446227784932308903L;

    /**
     * {@inheritDoc}
     */
    public FlightException(final String message) {
        super(message);
    }

}
