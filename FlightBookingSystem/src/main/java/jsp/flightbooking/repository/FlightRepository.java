package jsp.flightbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jsp.flightbooking.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
	public List<Flight> findBySourceAndDestination(String source, String destination);
	public List<Flight> findByAirline(String airline);
}
