package jsp.flightbooking.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters.LocalDateTimeToDateConverter;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.flightbooking.dto.ResponseStructure;
import jsp.flightbooking.entity.Booking;
import jsp.flightbooking.entity.Status;
import jsp.flightbooking.entity.Passenger;
import jsp.flightbooking.entity.Payment;
import jsp.flightbooking.service.BookingService;

@RequestMapping("/booking")
@RestController
public class BookingController {
	@Autowired
	private BookingService bookingService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Booking>> createBooking(@RequestBody Booking booking){
		return bookingService.createBooking(booking);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBooking(){
		return bookingService.getAllBooking();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Booking>> getBookingById(@PathVariable Integer id){
		return bookingService.getBookingById(id);
	}
	
	@GetMapping("/flightid/{flightId}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByFlight(@PathVariable Integer flightId){
		return bookingService.getBookingByFlight(flightId);
	}
	
	
	@GetMapping("/date/{date}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByDate(
	        @PathVariable  LocalDateTime date) {
	    return bookingService.getBookingByBookingDate(date);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(@PathVariable Status status){
		return bookingService.getBookingByStatus(status);
	}
	
	@GetMapping("/passenger/{id}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassengerByBookingId(@PathVariable Integer id){
		return bookingService.getAllPassengerByBookingId(id);
	}
	
	@GetMapping("/payment/{id}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentDetailsByBookingId(@PathVariable Integer id){
		return bookingService.getPaymentDetailsByBookingId(id);
	}
	
	@PutMapping("/{id}/{status}")
	public ResponseEntity<ResponseStructure<Booking>> updateBookingStatus(@PathVariable Integer id, @PathVariable Status status){
		return bookingService.updateBookingStatus(id, status);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBooking(@PathVariable Integer id){
		return bookingService.deleteBooking(id);
	}
	
	@GetMapping("/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Booking>>> getBookingByPaginationAndSorting(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field){
		return bookingService.getBookingByPaginationAndSorting(pageNumber, pageSize, field);
	}

}
