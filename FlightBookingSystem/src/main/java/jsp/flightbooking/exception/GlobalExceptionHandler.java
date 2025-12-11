package jsp.flightbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jsp.flightbooking.dto.ResponseStructure;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleINFE(IdNotFoundException exception){
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(exception.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoRecordAvailableException.class)
	public ResponseEntity<ResponseStructure<String>> handleNRAE(NoRecordAvailableException e){
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PassengerNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlePNFE(PassengerNotFoundException e){
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleFNFE(FlightNotFoundException e){
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlePaymenNFE(PaymentNotFoundException e){
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoBookingAvailableException.class)
	public ResponseEntity<ResponseStructure<String>> handleNBAE(NoBookingAvailableException e){
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(InvalidPaginationRequestException.class)
	public ResponseEntity<ResponseStructure<String>> handleIPRE(InvalidPaginationRequestException e){
		ResponseStructure<String> response = new ResponseStructure<String>();
		response.setStatuscode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(e.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}
}
