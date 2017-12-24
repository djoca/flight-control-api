package net.j33r.flightcontrol.domain.flight;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class represents an aircraft
 */
@Entity
@Table(name = "AIRCRAFT")
@Getter
@AllArgsConstructor
public class Aircraft {

    @Id
    @Column(name = "AIRCRAFT_ID")
    private final Long id;

    @Column(name = "AIRCRAFT_MANUFACTURER")
    private final String manufacturer;

    @Column(name = "AIRCRAFT_MODEL")
    private final String model;

    @Column(name = "AIRCRAFT_REGISTRY")
    private final String registry;

    @Column(name = "NUMBER_SEATS")
    private final Short numberSeats;

    @Column(name = "FLIGHT_HOURS")
    private final Integer flightHours;

    @Column(name = "FLIGHT_CYCLES")
    private final Integer flightCycles;

    /**
     * Default constructor required by JPA
     */
    @SuppressWarnings("unused")
    private Aircraft() {
        this(null, null, null, null, null, null, null);
    }

}
