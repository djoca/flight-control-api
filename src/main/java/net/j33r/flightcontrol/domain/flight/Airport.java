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
     * The airport identifier
     */
    @Id
    @Column(name = "AIRPORT_ID", nullable = false)
    private final Long id;

    /**
     * The airport IATA code.
     */
    @Column(name = "IATA_CODE", nullable = false)
    private final String iataCode;

    /**
     * The airport name
     */
    @Column(name = "AIRPORT_NAME", nullable = false)
    private final String name;

    /**
     * The airport city
     */
    @ManyToOne
    @JoinColumn(name = "CITY_ID", nullable = false)
    private final City city;

    /**
     * Default constructor required by JPA
     */
    @SuppressWarnings("unused")
    private Airport() {
        this(null, null, null, null);
    }

    /**
     * The identifier of the city where this airport resides
     *
     * @return the city identifier
     */
    public Long getCityId() {
        return city.getId();
    }

    /**
     * The name of the city where this airport resides
     *
     * @return the city identifier
     */
    public String getCityName() {
        return city.getName();
    }

}
