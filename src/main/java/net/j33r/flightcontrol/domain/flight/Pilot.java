package net.j33r.flightcontrol.domain.flight;

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

    @Id
    @Column(name = "PILOT_ID")
    private final Long id;

    @Column(name = "PILOT_NAME")
    private final String name;

    /**
     * Default constructor required by JPA
     */
    @SuppressWarnings("unused")
    private Pilot() {
        this(null, null);
    }

}
