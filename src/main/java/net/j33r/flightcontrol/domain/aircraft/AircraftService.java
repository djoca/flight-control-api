package net.j33r.flightcontrol.domain.aircraft;

import java.util.Collections;
import java.util.List;

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
     * Retrieve a {@link List} of all available aircrafts.
     *
     * @return a {@link List} of {@link Aircraft} objects
     */
    public List<Aircraft> retrieveAircrafts() {
        return Collections.unmodifiableList(repository.findAll());
    }

    /**
     * Retrieve an {@link Aircraft}
     *
     * @param id
     *            the {@link Aircraft} id.
     * @return an {@Link Aircraft} object
     */
    public Aircraft retrieveAircraft(final Long id) throws AircraftException {
        final Aircraft aircraft = repository.findOne(id);

        if (aircraft == null) {
            throw new AircraftException(String.format("Aircraft %s not found", id));
        }

        return aircraft;
    }

}
