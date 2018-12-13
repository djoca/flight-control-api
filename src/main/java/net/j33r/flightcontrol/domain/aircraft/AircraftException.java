package net.j33r.flightcontrol.domain.aircraft;

import net.j33r.flightcontrol.domain.FlightControlException;

/**
 * The {@link AircraftException} is the super class for the Aircraft domain
 * exceptions.
 */
public class AircraftException extends FlightControlException {

    private static final long serialVersionUID = 3796226470754140745L;

    /**
     * {@inheritDoc}
     */
    public AircraftException(final String message) {
        super(message);
    }
}
