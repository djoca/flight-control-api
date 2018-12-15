package net.j33r.flightcontrol.restapi.aircraft;

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
import org.springframework.web.context.WebApplicationContext;

import net.j33r.flightcontrol.config.SpringContextTestConfiguration;

/**
 * Aircraft API Integration tests
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringContextTestConfiguration.class })
@ActiveProfiles("test")
public class AircraftAPIControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(context).build();
    }

    /**
     * Tests the aircraft retrieval.
     *
     * @throws Exception
     *             if anything goes wrong
     */
    @Test
    public void retrieveAircrafts() throws Exception {
        final MockHttpServletResponse response = mockMvc.perform(get("/aircrafts")).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        final String jsonResponse = response.getContentAsString();
        final JSONArray jsonArray = new JSONArray(jsonResponse);

        assertTrue(jsonArray.length() == 2);

        assertAircraftObject(jsonArray.getJSONObject(0), 1L, "Embraer", "175", "PT-CAS");
        assertAircraftObject(jsonArray.getJSONObject(1), 2L, "Boeing", "737", "PP-GOL");
    }

    private void assertAircraftObject(final JSONObject jsonObject, final Long id, final String manufacturer,
            final String model, final String registry) throws Exception {
        assertEquals(4, jsonObject.length());
        assertEquals((long) id, jsonObject.getLong("id"));
        assertEquals(manufacturer, jsonObject.getString("manufacturer"));
        assertEquals(model, jsonObject.getString("model"));
        assertEquals(registry, jsonObject.getString("registry"));
    }
}
