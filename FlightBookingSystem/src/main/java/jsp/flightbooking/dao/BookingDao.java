package jsp.flightbooking.dao;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.FetchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import jsp.flightbooking.entity.Booking;
import jsp.flightbooking.entity.Status;
import jsp.flightbooking.entity.Flight;
import jsp.flightbooking.entity.Passenger;
import jsp.flightbooking.entity.Payment;
import jsp.flightbooking.exception.FlightNotFoundException;
import jsp.flightbooking.exception.IdNotFoundException;
import jsp.flightbooking.exception.InvalidPaginationRequestException;
import jsp.flightbooking.exception.NoBookingAvailableException;
import jsp.flightbooking.exception.NoRecordAvailableException;
import jsp.flightbooking.exception.PassengerNotFoundException;
import jsp.flightbooking.exception.PaymentNotFoundException;
import jsp.flightbooking.repository.BookingRepository;
import jsp.flightbooking.repository.FlightRepository;

@Repository
public class BookingDao {
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	
	public Booking createBooking(Booking booking) {
		return bookingRepository.save(booking);
		
	}
	
	public List<Booking> getAllBooking(){
		List<Booking> bookings =  bookingRepository.findAll();
		if(! bookings.isEmpty()) {
			return bookings;
		}
		else {
			throw new NoRecordAvailableException("No Booking Available to be displayed!!");
		}
	}
	
	public Booking getBookingById(Integer id) {
		Optional<Booking> opt = bookingRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new IdNotFoundException("No Record is found with id: "+id);
		}
	}
	
	public List<Booking> getBookingByFlight(Integer flightId){
		List<Booking> bookings = bookingRepository.findByFlightId(flightId);
		if(! bookings.isEmpty()) {
			return bookings;
		}
		else {
			throw new FlightNotFoundException("No Record is found with id: "+flightId);
		}
	}
	
	
	public List<Booking> getBookingByBookingDate(LocalDateTime date){
		List<Booking> bookings = bookingRepository.findByBookingDate(date);
		if(! bookings.isEmpty()) {
			return bookings;
		}
		else {
			throw new NoRecordAvailableException("Bookings not available for the given date: "+date);
		}
	}
	
	public List<Booking> getBookingByStatus(Status status){
		List<Booking> bookings = bookingRepository.findByStatus(status);
		if(! bookings.isEmpty()) {
			return bookings;
		}
		else {
			throw new NoRecordAvailableException("Bookings not available for the given status: "+status);
		}
	}
	
	public List<Passenger> getAllPassengerByBookingId(Integer id){
		Optional<Booking> booking = bookingRepository.findById(id);
		if(booking.isPresent()) {
			return booking.get().getPassengers();
		}
		else {
			throw new IdNotFoundException("Passengers with booking id "+id+" is not available");
		}
	}
	
	public Payment getPaymentDetailsByBookingId(Integer id){
		Optional<Booking> booking = bookingRepository.findById(id);
		if(booking.isPresent()) {
			return booking.get().getPayment();
		}
		else {
			throw new IdNotFoundException("Payment with booking id "+id+" is not available");
		}
	}
	
	public Booking updateBookingStatus(Integer id, Status status) {
		Optional<Booking> opt = bookingRepository.findById(id);
		if(opt.isPresent()) {
			Booking booking = opt.get();
			booking.setStatus(status);
			return bookingRepository.save(booking);
		}
		else {
			throw new  IdNotFoundException("Booking id "+id+" is not available to update the status");
		}
	}
	
	public String deleteBooking(Integer id) {
		Optional<Booking> opt = bookingRepository.findById(id);
		if(opt.isPresent()) {
			Booking booking = opt.get();
			bookingRepository.delete(booking);
			return "Booking deleted successfully";
		}
		else {
			throw new  IdNotFoundException("Booking id "+id+" is not available to delete booking");
		}
	}
	
	public Page<Booking> getBookingByPaginationAndSorting(Integer pageNumber, Integer pageSize, String field){
		
		 if (pageNumber < 0 || pageSize <= 0) {
			 throw new InvalidPaginationRequestException("Page number, page size and sort field must be provided and valid.");
		 }
		
		Page<Booking> bookings = bookingRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).descending()));
		if(! bookings.isEmpty()) {
			return bookings;
		}
		else {
			throw new NoBookingAvailableException("Booking data is not available");	
		}
	}
}
