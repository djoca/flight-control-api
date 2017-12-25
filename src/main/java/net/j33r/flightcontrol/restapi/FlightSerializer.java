package net.j33r.flightcontrol.restapi;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import net.j33r.flightcontrol.domain.flight.Flight;

/**
 * The class {@link FlightSerializer} is responsible for serialize a
 * {@link FlightWrapper} object into Json format.
 */
public class FlightSerializer extends JsonSerializer<FlightWrapper> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(final FlightWrapper flightWrapper, final JsonGenerator jsonGenerator,
            final SerializerProvider provider) throws IOException, JsonProcessingException {
        final Flight flight = flightWrapper.getFlight();

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", flight.getId());
        jsonGenerator.writeNumberField("flightNumber", flight.getNumber());
        jsonGenerator.writeStringField("companyName", flight.getCompanyName());

        jsonGenerator.writeObjectFieldStart("aircraft");
        jsonGenerator.writeNumberField("id", flight.getAircraftId());
        jsonGenerator.writeStringField("manufacturer", flight.getAircraftManufacturer());
        jsonGenerator.writeStringField("model", flight.getAircraftModel());
        jsonGenerator.writeStringField("registry", flight.getAircraftRegistry());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectFieldStart("pilot");
        jsonGenerator.writeNumberField("id", flight.getPilotId());
        jsonGenerator.writeStringField("name", flight.getPilotName());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectFieldStart("origin");
        jsonGenerator.writeNumberField("id", flight.getOriginAirportId());
        jsonGenerator.writeStringField("iataCode", flight.getOriginAirportIataCode());
        jsonGenerator.writeStringField("name", flight.getOriginAirportName());
        jsonGenerator.writeObjectFieldStart("city");
        jsonGenerator.writeNumberField("id", flight.getOriginCityId());
        jsonGenerator.writeStringField("name", flight.getOriginCityName());
        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectFieldStart("destination");
        jsonGenerator.writeNumberField("id", flight.getDestinationAirportId());
        jsonGenerator.writeStringField("iataCode", flight.getDestinationAirportIataCode());
        jsonGenerator.writeStringField("name", flight.getDestinationAirportName());
        jsonGenerator.writeObjectFieldStart("city");
        jsonGenerator.writeNumberField("id", flight.getDestinationCityId());
        jsonGenerator.writeStringField("name", flight.getDestinationCityName());
        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();

        jsonGenerator.writeStringField("flightStatus", flight.getStatusString());
        jsonGenerator.writeStringField("scheduledDepartureTime", flight.getFormattedScheduledDepartureTime());
        jsonGenerator.writeStringField("departureTime", flight.getFormattedDepartureTime());
        jsonGenerator.writeStringField("arrivalTime", flight.getFormattedArrivalTime());
        jsonGenerator.writeEndObject();
    }

}
