package net.j33r.flightcontrol.domain.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AirportRepository extends JpaRepository<Airport, Long> {

}
