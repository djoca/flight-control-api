package net.j33r.flightcontrol.config;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * This class contains the Spring context configuration for the tests.
 */
@ComponentScan(basePackages = "net.j33r.flightcontrol")
public class SpringContextTestConfiguration {

    /**
     * Creates and returns a mocked RestTemplate
     *
     * @return a mocked RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return mock(RestTemplate.class);
    }

}
