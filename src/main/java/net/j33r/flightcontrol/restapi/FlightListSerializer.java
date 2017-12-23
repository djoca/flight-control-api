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
public class FlightListSerializer extends JsonSerializer<FlightListWrapper> {

    @Override
    public void serialize(final FlightListWrapper flightWrapper, final JsonGenerator jsonGenerator,
            final SerializerProvider provider) throws IOException, JsonProcessingException {

        jsonGenerator.writeStartArray();

        for (final Flight flight : flightWrapper.getFlights()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", flight.getId());
            jsonGenerator.writeNumberField("flightNumber", flight.getNumber());
            jsonGenerator.writeStringField("companyName", flight.getCompanyName());
            jsonGenerator.writeStringField("origin", flight.getOrigin());
            jsonGenerator.writeStringField("destination", flight.getDestination());
            jsonGenerator.writeStringField("flightStatus", flight.getStatus());
            jsonGenerator.writeStringField("scheduledDepartureTime", flight.getFormattedScheduledDepartureTime());
            jsonGenerator.writeStringField("departureTime", flight.getFormattedDepartureTime());
            jsonGenerator.writeStringField("arrivalTime", flight.getFormattedArrivalTime());
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();
    }

}
