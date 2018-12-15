package net.j33r.flightcontrol.domain.airport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query("select a from #{#entityName} a join fetch a.city")
    List<Airport> findAllAirports();

    @Query("select a from #{#entityName} a join fetch a.city where a.id = :id")
    Airport findAirport(@Param("id") Long id);

}
