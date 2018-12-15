package net.j33r.flightcontrol.restapi.pilot;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import net.j33r.flightcontrol.domain.pilot.Pilot;

/**
 * The class {@link PilotListSerializer} is responsible for serialize an
 * {@link PilotListWrapper} object into Json format.
 */
class PilotListSerializer extends JsonSerializer<PilotListWrapper> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(final PilotListWrapper pilotWrapper, final JsonGenerator jsonGenerator,
            final SerializerProvider provider) throws IOException {

        jsonGenerator.writeStartArray();

        for (final Pilot pilot : pilotWrapper.getPilots()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", pilot.getId());
            jsonGenerator.writeStringField("name", pilot.getName());
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();
    }

}
