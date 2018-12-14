package net.j33r.flightcontrol.restapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import net.j33r.flightcontrol.config.SpringContextTestConfiguration;

/**
 * Flight Control API Integration tests
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringContextTestConfiguration.class })
@ActiveProfiles("test")
public class FlightControlAPIControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private DatabasePopulator databasePopulator;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setup() throws Exception {
        databasePopulator.populate(dataSource.getConnection());
        mockMvc = webAppContextSetup(context).build();
    }

    /**
     * Tests if the API returns a valid JSON response with flight information.
     *
     * @throws Exception
     *             if anything goes wrong
     */
    @Test
    public void retrieveFlights() throws Exception {
        final ResultActions action = mockMvc.perform(get("/flights"));
        final MockHttpServletResponse response = action.andReturn().getResponse();
        final String jsonResponse = response.getContentAsString();
        final JSONArray jsonArray = new JSONArray(jsonResponse);

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertTrue(jsonArray.length() == 3);

        final JSONObject jsonObject = jsonArray.getJSONObject(0);
        assertEquals(1, jsonObject.getInt("id"));
        assertEquals(123, jsonObject.getInt("flightNumber"));
        assertEquals("TAM", jsonObject.getString("companyName"));
        assertEquals("SJK", jsonObject.getString("origin"));
        assertEquals("RIO", jsonObject.getString("destination"));
        assertEquals("FLYING", jsonObject.getString("flightStatus"));
        assertEquals("21/12/2017 18:25", jsonObject.getString("departureTime"));
        assertTrue(jsonObject.isNull("arrivalTime"));
    }

    /**
     * Tests if the API returns a valid JSON response with detailed flight
     * information.
     *
     * @throws Exception
     *             if anything goes wrong
     */
    @Test
    public void retrieveFlight() throws Exception {
        final ResultActions action = mockMvc.perform(get("/flights/1"));
        final MockHttpServletResponse response = action.andReturn().getResponse();
        final String jsonResponse = response.getContentAsString();
        final JSONObject jsonObject = new JSONObject(jsonResponse);

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        assertEquals(1, jsonObject.getInt("id"));
        assertEquals(123, jsonObject.getInt("flightNumber"));
        assertEquals("TAM", jsonObject.getString("companyName"));

        // Aircraft
        final JSONObject aircraft = jsonObject.getJSONObject("aircraft");
        assertEquals(1, aircraft.getInt("id"));
        assertEquals("175", aircraft.getString("model"));
        assertEquals("Embraer", aircraft.getString("manufacturer"));
        assertEquals("PT-CAS", aircraft.getString("registry"));

        // Pilot
        final JSONObject pilot = jsonObject.getJSONObject("pilot");
        assertEquals(1, pilot.getInt("id"));
        assertEquals("Jack Black", pilot.getString("name"));

        // Origin
        final JSONObject origin = jsonObject.getJSONObject("origin");
        assertEquals(1, origin.getInt("id"));
        assertEquals("SJK", origin.getString("iataCode"));
        assertEquals("Aeroporto de São José dos Campos", origin.getString("name"));

        final JSONObject originCity = origin.getJSONObject("city");
        assertEquals(1, originCity.getInt("id"));
        assertEquals("São José dos Campos", originCity.getString("name"));

        // Destination
        final JSONObject destination = jsonObject.getJSONObject("destination");
        assertEquals(4, destination.getInt("id"));
        assertEquals("RIO", destination.getString("iataCode"));
        assertEquals("Aeroporto Internacional Antônio Carlos Jobim", destination.getString("name"));

        final JSONObject destinationCity = destination.getJSONObject("city");
        assertEquals(3, destinationCity.getInt("id"));
        assertEquals("Rio de Janeiro", destinationCity.getString("name"));

        assertEquals("FLYING", jsonObject.getString("flightStatus"));
        assertEquals("21/12/2017 18:25", jsonObject.getString("departureTime"));
        assertTrue(jsonObject.isNull("arrivalTime"));
    }

    /**
     * Tests the retrieval of inexistent flight
     *
     * @throws Exception
     */
    @Test
    public void retrieveInexistentFlight() throws Exception {
        final ResultActions action = mockMvc.perform(get("/flights/142"));
        final MockHttpServletResponse response = action.andReturn().getResponse();

        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    /**
     * Tests the creation of a flight
     *
     * @throws Exception
     */
    @Test
    public void flightCreation() throws Exception {
        final MockHttpServletResponse response = requestFlightCreation((short) 123, "TAM", 1L, 1L, 2L, 3L,
                "21/12/2017 18:25");

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    /**
     * Request a flight creation
     *
     * @param flightNumber
     *            the flight number
     * @param companyName
     *            the company name
     * @param aircraftId
     *            the aircraft id
     * @param pilotId
     *            the pilot id
     * @param originId
     *            the origin id
     * @param destinationId
     *            the destination id
     * @param departureTime
     *            the scheduled departure time
     * @return a JSON string
     */
    private MockHttpServletResponse requestFlightCreation(final Short flightNumber, final String companyName,
            final Long aircraftId, final Long pilotId, final Long originId, final Long destinationId,
            final String departureTime) throws Exception {
        final String baseJson = "{ \"flightNumber\": %s, \"companyName\": \"%s\", \"aircraft\": { \"id\": %s }, \"pilot\": { \"id\": %s }, \"origin\": { \"id\": %s }, \"destination\": { \"id\": %s }, \"departureTime\": \"%s\" }";
        final String json = String.format(baseJson, flightNumber, companyName, aircraftId, pilotId, originId,
                destinationId, departureTime);

        final ResultActions action = mockMvc
                .perform(post("/flights").content(json).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        return action.andReturn().getResponse();
    }
}
