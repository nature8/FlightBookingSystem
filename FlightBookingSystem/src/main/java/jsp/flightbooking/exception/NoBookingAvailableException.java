package jsp.flightbooking.exception;

public class NoBookingAvailableException extends RuntimeException {
	public NoBookingAvailableException(String msg){
		super(msg);
	}
}
