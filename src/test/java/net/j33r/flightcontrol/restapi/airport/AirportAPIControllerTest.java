package net.j33r.flightcontrol.restapi.airport;

import static org.junit.Assert.assertEquals;
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
import org.springframework.web.context.WebApplicationContext;

import net.j33r.flightcontrol.config.SpringContextTestConfiguration;

/**
 * Airport API Integration tests
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringContextTestConfiguration.class })
@ActiveProfiles("test")
public class AirportAPIControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(context).build();
    }

    /**
     * Tests the airport retrieval.
     *
     * @throws Exception
     *             if anything goes wrong
     */
    @Test
    public void retrieveAirports() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(get("/airports")).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        final String jsonResponse = response.getContentAsString();
        final JSONArray jsonArray = new JSONArray(jsonResponse);

        assertEquals(5, jsonArray.length());

        assertAirportObject(jsonArray.getJSONObject(0), 1L, "SJK", "São José dos Campos");
        assertAirportObject(jsonArray.getJSONObject(1), 2L, "GRU", "São Paulo");
        assertAirportObject(jsonArray.getJSONObject(2), 3L, "CGH", "São Paulo");
    }

    private void assertAirportObject(final JSONObject jsonObject, final Long id, final String iataCode,
            final String cityName) throws Exception {
        assertEquals(3, jsonObject.length());
        assertEquals((long) id, jsonObject.getLong("id"));
        assertEquals(iataCode, jsonObject.getString("iataCode"));
        assertEquals(cityName, jsonObject.getString("cityName"));
    }
}
