package jsp.flightbooking.exception;

public class PassengerNotFoundException extends RuntimeException {
	public PassengerNotFoundException(String msg) {
		super(msg);
	}
}
