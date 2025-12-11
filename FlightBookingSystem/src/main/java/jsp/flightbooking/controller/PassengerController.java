package jsp.flightbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.flightbooking.dto.ResponseStructure;
import jsp.flightbooking.entity.Passenger;
import jsp.flightbooking.service.PassengerService;

@RequestMapping("/passenger")
@RestController
public class PassengerController {
	@Autowired
	private PassengerService passengerService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Passenger>> addPassenger(Passenger passenger){
		return passengerService.addPassenger(passenger);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassenger(){
		return passengerService.getAllPassenger();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(@PathVariable Integer id){
		return passengerService.getPassengerById(id);
	}
	
	@GetMapping("/flight/{id}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengerByFlightId(Integer id){
		return passengerService.getPassengerByFlightId(id);
	}
	
	@GetMapping("/contact/{contact}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengerByContactNumber(@PathVariable String contact){
		return passengerService.getPassengerByContactNumber(contact);
	}
	
	@GetMapping("/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Passenger>>> getPassengerByPaginationAndSorting(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field){
		return passengerService.getPassengerByPaginationAndSorting(pageNumber, pageSize, field);
	}
}
