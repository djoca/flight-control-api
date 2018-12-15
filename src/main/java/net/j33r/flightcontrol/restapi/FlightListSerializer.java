package net.j33r.flightcontrol.restapi;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import net.j33r.flightcontrol.domain.flight.Flight;

/**
 * The class {@link FlightListSerializer} is responsible for serialize a
 * {@link FlightListWrapper} object into Json format.
 */
class FlightListSerializer extends JsonSerializer<FlightListWrapper> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(final FlightListWrapper flightWrapper, final JsonGenerator jsonGenerator,
            final SerializerProvider provider) throws IOException, JsonProcessingException {

        jsonGenerator.writeStartArray();

        for (final Flight flight : flightWrapper.getFlights()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", flight.getId());
            jsonGenerator.writeNumberField("flightNumber", flight.getNumber());
            jsonGenerator.writeStringField("companyName", flight.getCompanyName());
            jsonGenerator.writeStringField("origin", flight.getOriginAirportIataCode());
            jsonGenerator.writeStringField("destination", flight.getDestinationAirportIataCode());
            jsonGenerator.writeStringField("flightStatus", flight.getStatusString());
            jsonGenerator.writeStringField("departureTime", flight.getFormattedDepartureTime());
            jsonGenerator.writeStringField("arrivalTime", flight.getFormattedArrivalTime());
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();
    }

}
