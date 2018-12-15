package net.j33r.flightcontrol.restapi.aircraft;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import net.j33r.flightcontrol.domain.aircraft.Aircraft;

/**
 * The class {@link AircraftListSerializer} is responsible for serialize an
 * {@link AircraftListWrapper} object into Json format.
 */
class AircraftListSerializer extends JsonSerializer<AircraftListWrapper> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(final AircraftListWrapper aircraftWrapper, final JsonGenerator jsonGenerator,
            final SerializerProvider provider) throws IOException, JsonProcessingException {

        jsonGenerator.writeStartArray();

        for (final Aircraft aircraft : aircraftWrapper.getAircrafts()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", aircraft.getId());
            jsonGenerator.writeStringField("manufacturer", aircraft.getManufacturer());
            jsonGenerator.writeStringField("model", aircraft.getModel());
            jsonGenerator.writeStringField("registry", aircraft.getRegistry());
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();
    }

}
