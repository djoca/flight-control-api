package net.j33r.flightcontrol.restapi.aircraft;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.j33r.flightcontrol.domain.aircraft.Aircraft;

@Getter(AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@JsonSerialize(using = AircraftListSerializer.class)
class AircraftListWrapper {
    private final List<Aircraft> aircrafts;
}
