package jsp.flightbooking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jsp.flightbooking.entity.Booking;
import jsp.flightbooking.entity.Flight;
import jsp.flightbooking.exception.FlightNotFoundException;
import jsp.flightbooking.exception.IdNotFoundException;
import jsp.flightbooking.exception.InvalidPaginationRequestException;
import jsp.flightbooking.exception.NoBookingAvailableException;
import jsp.flightbooking.exception.NoRecordAvailableException;
import jsp.flightbooking.repository.FlightRepository;

@Repository
public class FlightDao {
	@Autowired
	private FlightRepository flightRepository;
	
	public Flight addFlight(Flight flight) {
		return flightRepository.save(flight);
	}
	
	public List<Flight> getAllFlights(){
		return flightRepository.findAll();
	}
	
	public Flight getFlightById(Integer id) {
		Optional<Flight> opt =  flightRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new IdNotFoundException("No record found with id "+id);
		}
	}
	
	public List<Flight> getFlightBySourceAndDestination(String source, String destination){
		 List<Flight> flights =  flightRepository.findBySourceAndDestination(source, destination);
		 if(! flights.isEmpty()) {
			 return flights;
		 }
		 else {
			 throw new NoRecordAvailableException("Record not available with source "+source+" and destination "+destination);
		 }
	}
	
	public List<Flight> getflightByAirline(String airline){
		List<Flight> flights =  flightRepository.findByAirline(airline);
		 if(! flights.isEmpty()) {
			 return flights;
		 }
		 else {
			 throw new NoRecordAvailableException("Record not available with airline "+airline);
		 }
	}
	
	public Flight updateFlight(Flight flight) {
		Optional<Flight> optional = flightRepository.findById(flight.getId());
		if(optional.isPresent()) {
			return flightRepository.save(flight);
		}
		else {
			throw new IdNotFoundException("Id does not exist in database. ");
		}
	}
	
	
	public String deleteFlight(Integer id) {
		Optional<Flight> opt = flightRepository.findById(id);
		if(opt.isPresent()) {
			flightRepository.delete(opt.get());
			return "Flight deleted successfully";
		}
		else {
	    	 throw new IdNotFoundException("Flight not found for id: " + id);
	     }
	}
	
public Page<Flight> getFlightByPaginationAndSorting(int pageNumber, int pageSize, String field){
		
		if (pageNumber < 0 || pageSize <= 0) {
			throw new InvalidPaginationRequestException("Page number, page size and sort field must be provided and valid.");
		}
		
		Page<Flight> flights = flightRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
		if(! flights.isEmpty()) {
			return flights;
		}
		else {
			throw new FlightNotFoundException("Flight data is not available");	
		}
	}
}
