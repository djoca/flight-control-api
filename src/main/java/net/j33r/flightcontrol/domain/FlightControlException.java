package net.j33r.flightcontrol.domain;

/**
 * The {@link FlightControlException} is the super class for all domain
 * exceptions.
 */
public class FlightControlException extends Exception {

    private static final long serialVersionUID = 1619277830459879795L;

    /**
     * {@inheritDoc}
     */
    public FlightControlException(final String message) {
        super(message);
    }

}
