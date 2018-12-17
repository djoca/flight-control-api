package net.j33r.flightcontrol.domain.flight;

/**
 * This interfaces defines the a flight action.
 */
interface FlightAction {

    /**
     * Execute a flight action
     *
     * @param flight
     *            the flight subject to execution
     * @throws FlightException
     *             if the action could not be executed.
     */
    void execute(Flight flight) throws FlightException;
}
