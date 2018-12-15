package net.j33r.flightcontrol.restapi.airport;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import net.j33r.flightcontrol.domain.airport.Airport;

/**
 * The class {@link AirportListSerializer} is responsible for serialize an
 * {@link AirportListWrapper} object into Json format.
 */
class AirportListSerializer extends JsonSerializer<AirportListWrapper> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(final AirportListWrapper airportWrapper, final JsonGenerator jsonGenerator,
            final SerializerProvider provider) throws IOException {

        jsonGenerator.writeStartArray();

        for (final Airport airport : airportWrapper.getAirports()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", airport.getId());
            jsonGenerator.writeStringField("iataCode", airport.getIataCode());
            jsonGenerator.writeStringField("cityName", airport.getCityName());
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();
    }

}
