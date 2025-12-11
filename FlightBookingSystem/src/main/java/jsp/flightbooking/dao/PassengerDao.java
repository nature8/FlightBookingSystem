package jsp.flightbooking.dao;

import java.util.List;

import java.util.Optional;

import javax.swing.text.AbstractDocument.BranchElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jsp.flightbooking.entity.Booking;
import jsp.flightbooking.entity.Flight;
import jsp.flightbooking.entity.Passenger;
import jsp.flightbooking.exception.FlightNotFoundException;
import jsp.flightbooking.exception.IdNotFoundException;
import jsp.flightbooking.exception.InvalidPaginationRequestException;
import jsp.flightbooking.exception.NoRecordAvailableException;
import jsp.flightbooking.exception.PassengerNotFoundException;
import jsp.flightbooking.repository.PassengerRepository;

@Repository
public class PassengerDao {
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	public Passenger addPassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
	}
	
	public List<Passenger> getAllPassenger(){
		List<Passenger> passengers = passengerRepository.findAll();
		if(! passengers.isEmpty()) {
			return passengers;
		}
		else {
			throw new PassengerNotFoundException("No passenger is available");
		}
	}
	
	public Passenger getPassengerById(Integer id) {
		Optional<Passenger> opt = passengerRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new IdNotFoundException("Id "+id+" not found");
		}
	}
	
	public List<Passenger> getPassengerByContactNumber(String contact) {
		List<Passenger> passenger = passengerRepository.findByContactNumber(contact);
		if(! passenger.isEmpty()) {
			return passenger;
		}
		else {
			throw new NoRecordAvailableException("Record not available with contact number "+contact);
		}
	}
	
	public List<Passenger> getPassengerByFlightId(Integer id){
		List<Passenger> passengers = passengerRepository.findByBookingFlightId(id);
		if(! passengers.isEmpty()) {
			return passengers;
		}
		else {
			throw new FlightNotFoundException("Flight not available with id: "+id);
		}
	}
	
	public Page<Passenger> getPassengerByPaginationAndSorting(int pageNumber, int pageSize, String field){
		
		if (pageNumber < 0 || pageSize <= 0) {
			 throw new InvalidPaginationRequestException("Page number, page size and sort field must be provided and valid.");
		}
		
		Page<Passenger> passengers = passengerRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
		if(! passengers.isEmpty()) {
			return passengers;
		}
		else {
			throw new PassengerNotFoundException("No passenger available with field "+field);
		}
	}
	
	
}
