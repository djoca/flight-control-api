package net.j33r.flightcontrol.domain.pilot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class represents a pilot
 */
@Entity
@Table(name = "PILOT")
@Getter
@AllArgsConstructor
public class Pilot {

    /**
     * The pilot identifier
     */
    @Id
    @Column(name = "PILOT_ID", nullable = false)
    private final Long id;

    /**
     * The pilot name
     */
    @Column(name = "PILOT_NAME", nullable = false)
    private final String name;

    /**
     * Default constructor required by JPA
     */
    @SuppressWarnings("unused")
    private Pilot() {
        this(null, null);
    }

}
