package net.j33r.flightcontrol.restapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightControlAPIController {

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    public String flight() {
        return null;
    }
}
