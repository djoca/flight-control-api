package net.j33r.flightcontrol.domain.flight;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class represents an airport.
 */
@Entity
@Table(name = "AIRPORT")
@Getter
@AllArgsConstructor
public class Airport {

    /**
     * Default constructor required by JPA
     */
    @SuppressWarnings("unused")
    private Airport() {
        this(null, null, null, null);
    }

    /**
     * The airport identifier
     */
    @Id
    @Column(name = "AIRPORT_ID")
    private final Long id;

    /**
     * The airport IATA code.
     */
    @Column(name = "IATA_CODE")
    private final String iataCode;

    /**
     * The airport name
     */
    @Column(name = "AIRPORT_NAME")
    private final String name;

    /**
     * The airport city
     */
    @ManyToOne
    @JoinColumn(name = "CITY_ID")
    private final City city;
}
