package net.j33r.flightcontrol.domain.flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * The class {@link FlightService} is the service layer for the Flight domain.
 */
@Service
public class FlightService {

    /**
     * Retrieve a {@link List} of {@link Flight} objects.
     *
     * @return a List of Flight objects
     */
    public List<Flight> retrieveFlights() {
        final List<Flight> flights = new ArrayList<>();

        flights.add(new Flight((long) 1, (short) 123, "Azul", "SJK", "BSB", "FLYING",
                LocalDateTime.of(LocalDate.of(2017, 12, 21), LocalTime.of(18, 20)),
                LocalDateTime.of(LocalDate.of(2017, 12, 21), LocalTime.of(18, 25)), null));

        flights.add(new Flight((long) 2, (short) 300, "Gol", "GRU", "POA", "LANDED",
                LocalDateTime.of(LocalDate.of(2017, 12, 21), LocalTime.of(14, 50)),
                LocalDateTime.of(LocalDate.of(2017, 12, 21), LocalTime.of(15, 0)),
                LocalDateTime.of(LocalDate.of(2017, 12, 21), LocalTime.of(17, 20))));

        return flights;
    }

}
