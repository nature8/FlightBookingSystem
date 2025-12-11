package jsp.flightbooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.flightbooking.dao.PassengerDao;
import jsp.flightbooking.dto.ResponseStructure;
import jsp.flightbooking.entity.Passenger;
import jsp.flightbooking.entity.Payment;
import jsp.flightbooking.exception.PassengerNotFoundException;

@Service
public class PassengerService {
	@Autowired
	private PassengerDao passengerDao;
	
	public ResponseEntity<ResponseStructure<Passenger>> addPassenger(Passenger passenger){
		ResponseStructure<Passenger> response = new ResponseStructure<Passenger>();
		response.setStatuscode(HttpStatus.CREATED.value());
		response.setMessage("Passenger records saved");
		response.setData(passengerDao.addPassenger(passenger));
		return new ResponseEntity<ResponseStructure<Passenger>>(response, HttpStatus.CREATED) ;
	}
	
	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassenger(){
		ResponseStructure<List<Passenger>> response = new ResponseStructure<List<Passenger>>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Passenger record fetched");
		response.setData(passengerDao.getAllPassenger());
		return new ResponseEntity<ResponseStructure<List<Passenger>>>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(Integer id){
		ResponseStructure<Passenger> response = new ResponseStructure<Passenger>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Passenger records fetched");
		response.setData(passengerDao.getPassengerById(id));
		return new ResponseEntity<ResponseStructure<Passenger>>(response, HttpStatus.OK) ;
	}
	
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengerByFlightId(Integer id){
		ResponseStructure<List<Passenger>> response = new ResponseStructure<List<Passenger>>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Passenger records fetched");
		response.setData(passengerDao.getPassengerByFlightId(id));
		return new ResponseEntity<ResponseStructure<List<Passenger>>>(response, HttpStatus.OK) ;
	}
	
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengerByContactNumber(String contact){
		ResponseStructure<List<Passenger>> response = new ResponseStructure<List<Passenger>>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Passenger records fetched");
		response.setData(passengerDao.getPassengerByContactNumber(contact));
		return new ResponseEntity<ResponseStructure<List<Passenger>>>(response, HttpStatus.OK) ;
	}
	
	public ResponseEntity<ResponseStructure<Page<Passenger>>> getPassengerByPaginationAndSorting(int pageNumber, int pageSize, String field){
		ResponseStructure<Page<Passenger>> response = new ResponseStructure<Page<Passenger>>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Passenger records fetched");
		response.setData(passengerDao.getPassengerByPaginationAndSorting(pageNumber, pageSize, field));
		return new ResponseEntity<ResponseStructure<Page<Passenger>>>(response, HttpStatus.OK) ;
	}
}
