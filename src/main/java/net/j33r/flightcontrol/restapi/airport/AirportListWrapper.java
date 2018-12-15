package net.j33r.flightcontrol.restapi.airport;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.j33r.flightcontrol.domain.airport.Airport;

@Getter(AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@JsonSerialize(using = AirportListSerializer.class)
class AirportListWrapper {

    private final List<Airport> airports;

}
