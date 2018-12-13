package net.j33r.flightcontrol.domain.pilot;

import net.j33r.flightcontrol.domain.FlightControlException;

/**
 * The {@link PilotException} is the super class for the Pilot domain
 * exceptions.
 */
public class PilotException extends FlightControlException {

    private static final long serialVersionUID = 4117433879966825144L;

    /**
     * {@inheritDoc}
     */
    public PilotException(final String message) {
        super(message);
    }
}
