package net.j33r.flightcontrol.domain.flight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class represents a Flight. This is the aggregate root of the Flight
 * domain.
 */
@Entity
@Table(name = "FLIGHT")
@Getter
@AllArgsConstructor
public class Flight {

    @SuppressWarnings("unused")
    private Flight() {
        this(null, null, null, null, null, null, null, null, null);
    }

    /**
     * The flight identifier
     */
    @Id
    @Column(name = "ID")
    private final Long id;

    /**
     * The flight number
     */
    @Column(name = "NUMBER")
    private final Short number;

    /**
     * The company name operating this flight
     */
    @Column(name = "COMPANY_NAME")
    private final String companyName;

    /**
     * The city of origin
     */
    @Column(name = "ORIGIN")
    private final String origin;

    /**
     * The city of destination
     */
    @Column(name = "DESTINATION")
    private final String destination;

    /**
     * The flight status
     */
    @Column(name = "STATUS")
    private final String status;

    /**
     * The date and time of the scheduled departure time
     */
    @Column(name = "SCHEDULED_DEPARTURE_TIME")
    private final LocalDateTime scheduledDepartureTime;

    /**
     * The date and time of departure
     */
    @Column(name = "DEPARTURE_TIME")
    private final LocalDateTime departureTime;

    /**
     * The date and time of actual arrival time
     */
    @Column(name = "ARRIVAL_TIME")
    private final LocalDateTime arrivalTime;

    /**
     * Retrieves the scheduled departure time with the format "dd/MM/yyyy HH:mm".
     *
     * @return a formated string
     */
    public String getFormattedScheduledDepartureTime() {
        return getFormattedDateTime(scheduledDepartureTime);
    }

    /**
     * Retrieves the departure time with the format "dd/MM/yyyy HH:mm".
     *
     * @return a formated string
     */
    public String getFormattedDepartureTime() {
        return getFormattedDateTime(departureTime);
    }

    /**
     * Retrieves the arrival time with the format "dd/MM/yyyy HH:mm".
     *
     * @return a formated string
     */
    public String getFormattedArrivalTime() {
        return getFormattedDateTime(arrivalTime);
    }

    /**
     * Converts a LocalDateTime into a String with the format "dd/MM/yyyy HH:mm".
     *
     * @param time
     *            a LocalDateTime object
     * @return a formated String
     */
    private String getFormattedDateTime(LocalDateTime time) {
        return time == null ? null : DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(time);
    }
}
