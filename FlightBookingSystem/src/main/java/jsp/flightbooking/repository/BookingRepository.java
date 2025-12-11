package jsp.flightbooking.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jsp.flightbooking.entity.Booking;
import jsp.flightbooking.entity.Status;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	List<Booking> findByFlightId(Integer flightId);
	List<Booking> findBybookingDate(LocalDateTime date);
	
	@Query("SELECT b FROM Booking b WHERE DATE(b.bookingDate) = :date")
	List<Booking> findByBookingDate(@Param("date") LocalDateTime date);
	
	List<Booking> findByStatus(Status status);
	
	

}
