package net.j33r.flightcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is responsible to boot the server
 */
@SpringBootApplication
public class Boot {

    public static void main(final String[] args) {
        SpringApplication.run(Boot.class, args);
    }
}