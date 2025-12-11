package jsp.flightbooking.exception;

public class InvalidPaginationRequestException extends RuntimeException{
	public InvalidPaginationRequestException(){
		
	}
	public InvalidPaginationRequestException(String msg){
		super(msg);
	}
}
