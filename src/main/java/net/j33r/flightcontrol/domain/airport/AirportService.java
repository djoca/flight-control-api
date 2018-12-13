package net.j33r.flightcontrol.domain.airport;

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
     * Retrieve an {@link Airport}
     *
     * @param id
     *            the {@link Airport} id.
     * @return an {@Link Airport} object
     */
    public Airport retrieveAirport(Long id) throws AirportException {
        final Airport airport = repository.findOne(id);

        if (airport == null) {
            throw new AirportException("Airport not found");
        }

        return airport;
    }

}
