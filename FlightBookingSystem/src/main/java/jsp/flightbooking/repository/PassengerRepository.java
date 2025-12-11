package jsp.flightbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.flightbooking.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
	List<Passenger> findByContactNumber(String contact);
	List<Passenger> findByBookingFlightId(Integer id);
}
