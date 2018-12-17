package net.j33r.flightcontrol.domain.flight;

/**
 * This class executes a flight delay action.
 */
class DelayFlightAction implements FlightAction {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Flight flight) throws FlightException {
        flight.delay();
    }

}
