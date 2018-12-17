package net.j33r.flightcontrol.domain.flight;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * The {@link FlightActionFactory} is a factory of {@link FlightAction} objects.
 */
@Component
class FlightActionFactory {

    private final Map<FlightActionType, FlightAction> actions;

    FlightActionFactory() {
        actions = new EnumMap<FlightActionType, FlightAction>(FlightActionType.class);
        actions.put(FlightActionType.DELAY, new DelayFlightAction());
        actions.put(FlightActionType.TAKE_OFF, new TakeOfflightAction());
        actions.put(FlightActionType.LAND, new LandFlightAction());

    }

    /**
     * Retrieves a {@link FlightAction} of a specified {@link FlightActionType}
     *
     * @param actionType
     *            the {@link FlightActionType}
     * @return a {@link FlightAction}
     */
    FlightAction get(final FlightActionType actionType) {
        return actions.get(actionType);
    }
}
