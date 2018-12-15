package net.j33r.flightcontrol.domain.pilot;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * The class {@link PilotService} is the service layer for the Pilot domain.
 */
@Service
@AllArgsConstructor
public class PilotService {

    private final PilotRepository repository;

    /**
     * Retrieve a {@link List} of all available pilots.
     *
     * @return a {@link List} of {@link Pilot} objects
     */
    public List<Pilot> retrievePilots() {
        return Collections.unmodifiableList(repository.findAll());
    }

    /**
     * Retrieve an {@link Pilot}
     *
     * @param id
     *            the {@link Pilot} id.
     * @return an {@Link Pilot} object
     */
    public Pilot retrievePilot(final Long id) throws PilotException {
        final Pilot pilot = repository.findOne(id);

        if (pilot == null) {
            throw new PilotException(String.format("Pilot %s not found", id));
        }

        return pilot;
    }

}
