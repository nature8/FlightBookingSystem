package jsp.flightbooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import jsp.flightbooking.dao.FlightDao;
import jsp.flightbooking.dto.ResponseStructure;
import jsp.flightbooking.entity.Flight;

@Service
public class FlightService {
	
	@Autowired
	private FlightDao flightDao;
	
	public ResponseEntity<ResponseStructure<Flight>> addFlight(Flight flight){
		Flight flights =  flightDao.addFlight(flight);
		ResponseStructure<Flight> response=new ResponseStructure<Flight>();
		response.setStatuscode(HttpStatus.CREATED.value());
		response.setMessage("Flight records saved");
		response.setData(flights);
		return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.CREATED) ;
	}
	
	public ResponseEntity<ResponseStructure<List<Flight>>> getAllFlights(){
		ResponseStructure<List<Flight>> response=new ResponseStructure<List<Flight>>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Flight records fetched");
		response.setData(flightDao.getAllFlights());
		return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.CREATED) ;
	}
	
	public ResponseEntity<ResponseStructure<Flight>> getFlightById(Integer id){
		ResponseStructure<Flight> response=new ResponseStructure<Flight>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Flight records fetched");
		response.setData(flightDao.getFlightById(id));
		return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.OK) ;
	}
	
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightBySourceAndDestination(String source, String destination){
		ResponseStructure<List<Flight>> response=new ResponseStructure<List<Flight>>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Flight records fetched");
		response.setData(flightDao.getFlightBySourceAndDestination(source, destination));
		return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.OK) ;
	}
	
	public ResponseEntity<ResponseStructure<List<Flight>>> getflightByAirline(String airline){
		ResponseStructure<List<Flight>> response=new ResponseStructure<List<Flight>>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Flight records fetched");
		response.setData(flightDao.getflightByAirline(airline));
		return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.OK) ;
	}
	
	public ResponseEntity<ResponseStructure<Flight>> updateFlight(Flight flight){
		ResponseStructure<Flight> response=new ResponseStructure<Flight>();
		if(flight.getId()== null) {
			response.setStatuscode(HttpStatus.NOT_FOUND.value());
	        response.setMessage("Id must be passed to update a record");
	        response.setData(null);
		}
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Flight records updated");
		response.setData(flightDao.updateFlight(flight));
		return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.OK) ;
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteFlight(Integer id){
		ResponseStructure<String> response = new ResponseStructure<>();
		flightDao.deleteFlight(id);
	    response.setStatuscode(HttpStatus.OK.value());
	    response.setMessage("Flight deleted");
	    response.setData("Success"); 
	    return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Page<Flight>>> getFlightByPaginationAndSorting(int pageNumber, int pageSize, String field){
		ResponseStructure<Page<Flight>> response=new ResponseStructure<Page<Flight>>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Flight records fetched");
		response.setData(flightDao.getFlightByPaginationAndSorting(pageNumber, pageSize, field));
		return new ResponseEntity<ResponseStructure<Page<Flight>>>(response, HttpStatus.OK) ;
	}
}
