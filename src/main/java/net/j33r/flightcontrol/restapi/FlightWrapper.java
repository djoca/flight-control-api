package net.j33r.flightcontrol.restapi;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AccessLevel;
import lombok.Getter;
import net.j33r.flightcontrol.domain.flight.Flight;

/**
 * This class is a wrapper for Flight objects. This class makes possible to
 * separate the domain class and its serialization responsibilities.
 */
@Getter(AccessLevel.PACKAGE)
@JsonSerialize(using = FlightSerializer.class)
@JsonDeserialize(using = FlightDeserializer.class)
class FlightWrapper {

    private Flight flight;

    private Short number;

    private String companyName;

    private Long aircraftId;

    private Long pilotId;

    private Long originId;

    private Long destinationId;

    private String departureTime;

    FlightWrapper(final Flight flight) {
        this.flight = flight;
    }

    FlightWrapper(final Short number, final String companyName, final Long aircraftId, final Long pilotId,
            final Long originId, final Long destinationId, final String departureTime) {
        this.number = number;
        this.companyName = companyName;
        this.aircraftId = aircraftId;
        this.pilotId = pilotId;
        this.originId = originId;
        this.destinationId = destinationId;
        this.departureTime = departureTime;
    }
}
