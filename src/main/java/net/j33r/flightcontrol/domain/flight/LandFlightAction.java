package net.j33r.flightcontrol.domain.flight;

/**
 * This class executes a land flight action.
 */
class LandFlightAction implements FlightAction {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Flight flight) throws FlightException {
        flight.land();
    }

}
