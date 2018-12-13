package net.j33r.flightcontrol.domain.aircraft;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * The class {@link AircraftService} is the service layer for the Aircraft
 * domain.
 */
@Service
@AllArgsConstructor
public class AircraftService {

    private final AircraftRepository repository;

    /**
     * Retrieve an {@link Aircraft}
     *
     * @param id
     *            the {@link Aircraft} id.
     * @return an {@Link Aircraft} object
     */
    public Aircraft retrieveAircraft(Long id) throws AircraftException {
        final Aircraft aircraft = repository.findOne(id);

        if (aircraft == null) {
            throw new AircraftException("Aircraft not found");
        }

        return aircraft;
    }
}
