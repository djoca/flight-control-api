package net.j33r.flightcontrol.domain.flight;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("select f from Flight f join fetch f.origin o join fetch f.pilot join fetch f.aircraft "
            + " join fetch f.destination d join fetch o.city join fetch d.city")
    List<Flight> findAllFlights();

    @Query("select f from Flight f join fetch f.origin o join fetch f.pilot join fetch f.aircraft "
            + " join fetch f.destination d join fetch o.city join fetch d.city where f.id = :id")
    Flight findFlight(@Param("id") Long id);

    @Query("select f from Flight f join fetch f.origin o join fetch f.pilot join fetch f.aircraft "
            + " join fetch f.destination d join fetch o.city join fetch d.city"
            + " where upper(o.iataCode) like concat('%',upper(:search),'%') or upper(d.iataCode) like concat('%',upper(:search),'%') "
            + "    or upper(o.city.name) like concat('%',upper(:search),'%') or upper(d.city.name) like concat('%',upper(:search),'%')"
            + "    or upper(f.companyName) like concat('%',upper(:search),'%') or f.status like concat('%',upper(:search),'%')")
    List<Flight> searchFlights(@Param("search") String search);
}
