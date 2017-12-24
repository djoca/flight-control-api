package net.j33r.flightcontrol.domain.flight;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    /**
     * Default constructor required by JPA
     */
    @SuppressWarnings("unused")
    private Flight() {
        this(null, null, null, null, null, null, null, null, null, null, null);
    }

    /**
     * The flight identifier
     */
    @Id
    @Column(name = "FLIGHT_ID")
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
     * The flight aircraft
     */
    @ManyToOne
    @JoinColumn(name = "AIRCRAFT_ID")
    private final Aircraft aircraft;

    /**
     * The flight pilot
     */
    @ManyToOne
    @JoinColumn(name = "PILOT_ID")
    private final Pilot pilot;

    /**
     * The city of origin
     */
    @ManyToOne
    @JoinColumn(name = "ORIGIN_AIRPORT_ID")
    private final Airport origin;

    /**
     * The city of destination
     */
    @ManyToOne
    @JoinColumn(name = "DESTINATION_AIRPORT_ID")
    private final Airport destination;

    /**
     * The flight status
     */
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private final FlightStatus status;

    /**
     * The date and time of the scheduled departure time
     */
    @Embedded
    @AttributeOverride(name = "dateTime", column = @Column(name = "SCHEDULED_DEPARTURE_TIME"))
    private final FlightDateTime scheduledDepartureTime;

    /**
     * The date and time of departure
     */
    @Embedded
    @AttributeOverride(name = "dateTime", column = @Column(name = "DEPARTURE_TIME"))
    private final FlightDateTime departureTime;

    /**
     * The date and time of actual arrival time
     */
    @Embedded
    @AttributeOverride(name = "dateTime", column = @Column(name = "ARRIVAL_TIME"))
    private final FlightDateTime arrivalTime;

    /**
     * Retrieves the scheduled departure time with the format "dd/MM/yyyy
     * HH:mm".
     *
     * @return a formated string
     */
    public String getFormattedScheduledDepartureTime() {
        return scheduledDepartureTime == null ? null : scheduledDepartureTime.getFormattedDateTime();
    }

    /**
     * Retrieves the departure time with the format "dd/MM/yyyy HH:mm".
     *
     * @return a formated string
     */
    public String getFormattedDepartureTime() {
        return departureTime == null ? null : departureTime.getFormattedDateTime();
    }

    /**
     * Retrieves the arrival time with the format "dd/MM/yyyy HH:mm".
     *
     * @return a formated string
     */
    public String getFormattedArrivalTime() {
        return arrivalTime == null ? null : arrivalTime.getFormattedDateTime();
    }

    /**
     * Returns the origin airport IATA code.
     *
     * @return the IATA code.
     */
    public String getOriginAirportIataCode() {
        return origin.getIataCode();
    }

    /**
     * Returns the destination airport IATA code.
     *
     * @return the IATA code.
     */
    public String getDestinationAirportIataCode() {
        return destination.getIataCode();
    }

    /**
     * Returns the flight status as a String
     *
     * @return the flight status
     */
    public String getStatusString() {
        return status.toString();
    }

}
