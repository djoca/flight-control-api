package net.j33r.flightcontrol.domain.airport;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * The class {@link AirportService} is the service layer for the Airport domain.
 */
@Service
@AllArgsConstructor
public class AirportService {

    private final AirportRepository repository;

    /**
     * Retrieve a {@link List} of all available airports.
     *
     * @return a {@link List} of {@link Airport} objects
     */
    public List<Airport> retrieveAirports() {
        return repository.findAllAirports();
    }

    /**
     * Retrieve an {@link Airport}
     *
     * @param id
     *            the {@link Airport} id.
     * @return an {@Link Airport} object
     */
    public Airport retrieveAirport(final Long id) throws AirportException {
        final Airport airport = repository.findAirport(id);

        if (airport == null) {
            throw new AirportException(String.format("Airport %s not found", id));
        }

        return airport;
    }

}
