package net.j33r.flightcontrol.domain.flight;

/**
 * This class executes a take off flight action.
 */
class TakeOfflightAction implements FlightAction {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Flight flight) throws FlightException {
        flight.takeOff();
    }

}
