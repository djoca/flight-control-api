package net.j33r.flightcontrol.domain.flight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The class {@link FlightDateTime} represents the date and time of a flight.
 */
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
@ToString
public class FlightDateTime {

    private static final String DATE_PATTERN = "dd/MM/yyyy HH:mm";

    private final LocalDateTime dateTime;

    FlightDateTime(final int year, final int month, final int day, final int hour, final int minutes) {
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
    String getFormattedDateTime() {
        return DateTimeFormatter.ofPattern(DATE_PATTERN).format(dateTime);
    }

    /**
     * Returns a FlightDateTime representing the present instant.
     *
     * @return the present FlightDateTime
     */
    static FlightDateTime now() {
        return new FlightDateTime(LocalDateTime.now());
    }

    /**
     * Parses a String into a {@link FlightDateTime} object.
     *
     * @param date
     *            a String with the "dd/MM/yyyy HH:mm" pattern.
     * @return a {@link FlightDateTime} object
     */
    public static FlightDateTime parse(final String date) {
        final LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN));
        return new FlightDateTime(localDateTime);
    }

    /**
     * Verifies if this {@link FlightDateTime} is in the past.
     *
     * @return true if this {@link FlightDateTime} is in the past. Otherwise
     *         returns false.
     */
    public boolean isPast() {
        return dateTime.isBefore(LocalDateTime.now());
    }

}
