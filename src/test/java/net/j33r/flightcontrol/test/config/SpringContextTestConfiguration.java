package net.j33r.flightcontrol.test.config;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = "net.j33r.flightcontrol")
public class SpringContextTestConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return mock(RestTemplate.class);
    }

}
