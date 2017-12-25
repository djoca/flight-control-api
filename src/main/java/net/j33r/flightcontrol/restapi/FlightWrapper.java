package net.j33r.flightcontrol.restapi;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.j33r.flightcontrol.domain.flight.Flight;

/**
 * This class is a wrapper for Flight objects. This class makes possible to
 * separate the domain class and its serialization responsibilities.
 */
@Getter
@AllArgsConstructor
@JsonSerialize(using = FlightSerializer.class)
public class FlightWrapper {

    private final Flight flight;
}
