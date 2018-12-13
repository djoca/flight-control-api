package net.j33r.flightcontrol.domain.aircraft;

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

    /**
     * The aircraft identifier
     */
    @Id
    @Column(name = "AIRCRAFT_ID", nullable = false)
    private final Long id;

    /**
     * The aircraft manufacturer
     */
    @Column(name = "AIRCRAFT_MANUFACTURER", nullable = false)
    private final String manufacturer;

    /**
     * The aircraft model
     */
    @Column(name = "AIRCRAFT_MODEL", nullable = false)
    private final String model;

    /**
     * The aircraft registry
     */
    @Column(name = "AIRCRAFT_REGISTRY", nullable = false)
    private final String registry;

    /**
     * The aircraft number of seats
     */
    @Column(name = "NUMBER_SEATS", nullable = false)
    private final Short numberSeats;

    /**
     * The aircraft number of flight hours
     */
    @Column(name = "FLIGHT_HOURS", nullable = false)
    private final Integer flightHours;

    /**
     * The aircraft number of flight cycles
     */
    @Column(name = "FLIGHT_CYCLES", nullable = false)
    private final Integer flightCycles;

    /**
     * Default constructor required by JPA
     */
    @SuppressWarnings("unused")
    private Aircraft() {
        this(null, null, null, null, null, null, null);
    }

}
