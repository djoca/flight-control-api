package net.j33r.flightcontrol.restapi.flight;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.j33r.flightcontrol.domain.flight.Flight;

/**
 * This class is a wrapper for a list of Flight objects. This class makes
 * possible to separate the domain class and its serialization responsibilities.
 */
@Getter(AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@JsonSerialize(using = FlightListSerializer.class)
class FlightListWrapper {

    private final List<Flight> flights;

}
