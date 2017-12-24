package net.j33r.flightcontrol.test.restapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import net.j33r.flightcontrol.test.config.SpringContextTestConfiguration;

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

    @Before
    public void setup() throws Exception {
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

        // {id: %s, flightNumber: '%s', companyName: '%s', origin: '%s',
        // destination: '%s', flightStatus: '%s', departureTime: '%s'}

        final JSONObject jsonObject = jsonArray.getJSONObject(0);
        assertEquals(1, jsonObject.getInt("id"));
        assertEquals(123, jsonObject.getInt("flightNumber"));
        assertEquals("TAM", jsonObject.getString("companyName"));
        assertEquals("SJK", jsonObject.getString("origin"));
        assertEquals("RIO", jsonObject.getString("destination"));
        assertEquals("FLYING", jsonObject.getString("flightStatus"));
        assertEquals("21/12/2017 18:20", jsonObject.getString("scheduledDepartureTime"));
        assertEquals("21/12/2017 18:25", jsonObject.getString("departureTime"));
        assertTrue(jsonObject.isNull("arrivalTime"));

        assertTrue(jsonArray.length() == 2);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

}
