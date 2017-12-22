package net.j33r.flightcontrol.domain.flight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class represents a Flight. This is the aggregate root of the Flight
 * domain.
 */
@Getter
@AllArgsConstructor
public class Flight {

    /**
     * The flight identifier
     */
    private final Long id;

    /**
     * The flight number
     */
    private final Short number;

    /**
     * The company name operating this flight
     */
    private final String companyName;

    /**
     * The city of origin
     */
    private final String origin;

    /**
     * The city of destination
     */
    private final String destination;

    /**
     * The flight status
     */
    private final String status;

    /**
     * The date and time of the scheduled departure time
     */
    private final LocalDateTime scheduledDepartureTime;

    /**
     * The date and time of departure
     */
    private final LocalDateTime departureTime;

    /**
     * The date and time of actual arrival time
     */
    private final LocalDateTime arrivalTime;

    public String getFormatedDepartureTime() {
        return departureTime == null ? null : DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(departureTime);
    }

}
