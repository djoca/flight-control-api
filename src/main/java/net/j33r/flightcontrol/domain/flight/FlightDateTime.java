package net.j33r.flightcontrol.domain.flight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;

/**
 * The class {@link FlightDateTime} represents the date and time of a flight.
 */
@AllArgsConstructor
@Embeddable
public class FlightDateTime {

    private final LocalDateTime dateTime;

    public FlightDateTime(final int year, final int month, final int day, final int hour, final int minutes) {
        dateTime = LocalDateTime.of(year, month, day, hour, minutes);
    }

    /**
     * Default constructor required by JPA
     */
    @SuppressWarnings("unused")
    private FlightDateTime() {
        this(null);
    }

    /**
     * Returns the date and time as a String with the format "dd/MM/yyyy HH:mm".
     *
     * @return a formated String
     */
    public String getFormattedDateTime() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(dateTime);
    }

}
