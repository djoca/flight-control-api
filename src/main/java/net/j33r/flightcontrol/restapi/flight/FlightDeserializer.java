package net.j33r.flightcontrol.restapi.flight;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * The class {@link FlightDeserializer} is responsible for parsing of json
 * string into a {@link FlightWrapper} object.
 */
class FlightDeserializer extends JsonDeserializer<FlightWrapper> {

    /**
     * {@inheritDoc}
     */
    @Override
    public FlightWrapper deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
        final ObjectCodec codec = parser.getCodec();
        final JsonNode node = codec.readTree(parser);

        final Short number = node.get("flightNumber").shortValue();
        final String companyName = node.get("companyName").asText();
        final Long aircraftId = node.get("aircraft").get("id").longValue();
        final Long pilotId = node.get("pilot").get("id").longValue();
        final Long originId = node.get("origin").get("id").longValue();
        final Long destinationId = node.get("destination").get("id").longValue();
        final String departureTime = node.get("departureTime").asText();

        return new FlightWrapper(number, companyName, aircraftId, pilotId, originId, destinationId, departureTime);
    }

}
