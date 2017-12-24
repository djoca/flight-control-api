package net.j33r.flightcontrol.domain.flight;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class represents a city.
 */
@Entity
@Table(name = "CITY")
@Getter
@AllArgsConstructor
public class City {

    /**
     * Default constructor required by JPA
     */
    @SuppressWarnings("unused")
    private City() {
        this(null, null);
    }

    /**
     * The city identifier
     */
    @Id
    @Column(name = "CITY_ID")
    private final Long id;

    /**
     * The city name
     */
    @Column(name = "CITY_NAME")
    private final String name;

}
