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

import lombok.AccessLevel;
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
     * The flight identifier
     */
    @Id
    @Column(name = "FLIGHT_ID", nullable = false)
    private final Long id;

    /**
     * The flight number
     */
    @Column(name = "NUMBER", nullable = false)
    private final Short number;

    /**
     * The company name operating this flight
     */
    @Column(name = "COMPANY_NAME", nullable = false)
    private final String companyName;

    /**
     * The flight aircraft
     */
    @ManyToOne
    @JoinColumn(name = "AIRCRAFT_ID", nullable = false)
    private final Aircraft aircraft;

    /**
     * The flight pilot
     */
    @ManyToOne
    @JoinColumn(name = "PILOT_ID", nullable = false)
    private final Pilot pilot;

    /**
     * The city of origin
     */
    @ManyToOne
    @JoinColumn(name = "ORIGIN_AIRPORT_ID", nullable = false)
    private final Airport origin;

    /**
     * The city of destination
     */
    @ManyToOne
    @JoinColumn(name = "DESTINATION_AIRPORT_ID", nullable = false)
    private final Airport destination;

    /**
     * The flight status
     */
    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private final FlightStatus status;

    /**
     * The date and time of the scheduled departure time
     */
    @Embedded
    @AttributeOverride(name = "dateTime", column = @Column(name = "SCHEDULED_DEPARTURE_TIME", nullable = false))
    @Getter(AccessLevel.NONE)
    private final FlightDateTime scheduledDepartureTime;

    /**
     * The date and time of departure
     */
    @Embedded
    @AttributeOverride(name = "dateTime", column = @Column(name = "DEPARTURE_TIME"))
    @Getter(AccessLevel.NONE)
    private final FlightDateTime departureTime;

    /**
     * The date and time of actual arrival time
     */
    @Embedded
    @AttributeOverride(name = "dateTime", column = @Column(name = "ARRIVAL_TIME"))
    private final FlightDateTime arrivalTime;

    /**
     * Default constructor required by JPA
     */
    @SuppressWarnings("unused")
    private Flight() {
        this(null, null, null, null, null, null, null, null, null, null, null);
    }

    /**
     * The departure time is the actual departure time if the aircraft did take
     * off or the scheduled departure time if it is on the ground yet.
     *
     * @return the actual departure time.
     */
    public FlightDateTime getDepartureTime() {
        return departureTime == null ? scheduledDepartureTime : departureTime;
    }

    /**
     * Retrieves the departure time with the format "dd/MM/yyyy HH:mm".
     *
     * @return a formated string
     */
    public String getFormattedDepartureTime() {
        return getDepartureTime().getFormattedDateTime();
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

    /**
     * Returns the origin airport identifier
     *
     * @return the airport identifier
     */
    public Long getOriginAirportId() {
        return origin.getId();
    }

    /**
     * Returns the origin airport name
     *
     * @return the airport name
     */
    public String getOriginAirportName() {
        return origin.getName();
    }

    /**
     * Returns the origin city identifier
     *
     * @return the city identifier
     */
    public Long getOriginCityId() {
        return origin.getCityId();
    }

    /**
     * Returns the origin airport name
     *
     * @return the airport name
     */
    public String getOriginCityName() {
        return origin.getCityName();
    }

    /**
     * Returns the destination airport identifier
     *
     * @return the airport identifier
     */
    public Long getDestinationAirportId() {
        return destination.getId();
    }

    /**
     * Returns the destination airport name
     *
     * @return the airport name
     */
    public String getDestinationAirportName() {
        return destination.getName();
    }

    /**
     * Returns the destination city identifier
     *
     * @return the city identifier
     */
    public Long getDestinationCityId() {
        return destination.getCityId();
    }

    /**
     * Returns the destination city name
     *
     * @return the city name
     */
    public String getDestinationCityName() {
        return destination.getCityName();
    }

    /**
     * Returns the identifier of the aircraft used on this flight
     *
     * @return the aircraft identifier
     */
    public Long getAircraftId() {
        return aircraft.getId();
    }

    /**
     * Returns the manufacturer of the aircraft used on this flight
     *
     * @return the aircraft manufacturer
     */
    public String getAircraftManufacturer() {
        return aircraft.getManufacturer();
    }

    /**
     * Returns the model of the aircraft used on this flight
     *
     * @return the aircraft model
     */
    public String getAircraftModel() {
        return aircraft.getModel();
    }

    /**
     * Returns the registry of the aircraft used on this flight
     *
     * @return the aircraft registry
     */
    public String getAircraftRegistry() {
        return aircraft.getRegistry();
    }

    /**
     * Returns the pilot identifier responsible for this flight
     *
     * @return the pilot identifier
     */
    public Long getPilotId() {
        return pilot.getId();
    }

    /**
     * Returns the pilot name responsible for this flight
     *
     * @return the pilot name
     */
    public String getPilotName() {
        return pilot.getName();
    }

}
