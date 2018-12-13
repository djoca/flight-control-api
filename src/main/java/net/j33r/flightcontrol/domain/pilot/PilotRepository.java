package net.j33r.flightcontrol.domain.pilot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PilotRepository extends JpaRepository<Pilot, Long> {

}
