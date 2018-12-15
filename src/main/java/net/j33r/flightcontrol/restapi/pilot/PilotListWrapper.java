package net.j33r.flightcontrol.restapi.pilot;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.j33r.flightcontrol.domain.pilot.Pilot;

@Getter(AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@JsonSerialize(using = PilotListSerializer.class)
class PilotListWrapper {

    private final List<Pilot> pilots;

}
