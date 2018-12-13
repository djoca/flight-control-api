package net.j33r.flightcontrol.domain.aircraft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AircraftRepository extends JpaRepository<Aircraft, Long> {

}
