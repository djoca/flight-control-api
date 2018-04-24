package net.j33r.flightcontrol.domain.flight;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("select f from Flight f join fetch f.origin o join fetch f.pilot join fetch f.aircraft "
            + " join fetch f.destination d join fetch o.city join fetch d.city")
    List<Flight> findAllFlights();
}
