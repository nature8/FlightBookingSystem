package jsp.flightbooking.dao;

import java.util.List;
import java.util.Optional;

import jsp.flightbooking.entity.Status;
import jsp.flightbooking.entity.TransactionMode;

import org.hibernate.event.internal.DefaultResolveNaturalIdEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jsp.flightbooking.entity.Booking;
import jsp.flightbooking.entity.Passenger;
import jsp.flightbooking.entity.Payment;
import jsp.flightbooking.exception.IdNotFoundException;
import jsp.flightbooking.exception.InvalidPaginationRequestException;
import jsp.flightbooking.exception.NoRecordAvailableException;
import jsp.flightbooking.exception.PassengerNotFoundException;
import jsp.flightbooking.exception.PaymentNotFoundException;
import jsp.flightbooking.repository.PaymentRepository;

@Repository
public class PaymentDao {
	@Autowired
	private PaymentRepository paymentRepository;
	
	public Payment recordPayment(Payment payment) {
		return paymentRepository.save(payment);
	}
	
	public List<Payment> getAllPayment(){
		List<Payment> payment =  paymentRepository.findAll();
		if(! payment.isEmpty()) {
			return payment;
		}
		else {
			throw new NoRecordAvailableException("No Payment detail is available");
		}
	}
	
	public Payment getPaymentById(Integer id) {
		Optional<Payment> opt = paymentRepository.findById(id);
		if(opt.isEmpty()) {
			throw new IdNotFoundException("Id "+id+" not found");
		}
		else {
			return opt.get();
		}
	}
	
	public List<Payment> getPaymentByStatus(Status status){
		List<Payment> payment = paymentRepository.findByStatus(status);
		if(! payment.isEmpty()) {
			return payment;
		}
		else {
			throw new NoRecordAvailableException("No record available with status "+status);
		}
	}
	
	public List<Payment> getPaymentByModeOfTransaction(TransactionMode mode){
		List<Payment> payment =  paymentRepository.findByModeOfTransaction(mode);
		if(! payment.isEmpty()) {
			return payment;
		}
		else {
			throw new NoRecordAvailableException("No record available with mode of transaction "+mode);
		}
	}
	
	public Payment getPaymentByBookingId(Integer bookingId) {
		Payment payment = paymentRepository.findByBookingId(bookingId);
		if(payment != null) {
			return payment;
		}
		else {
			throw new IdNotFoundException("Booking id "+bookingId+" no found");
		}
	}
	
	public Payment updatePaymentStatus(Integer id, Status status) {
		Optional<Payment> opt = paymentRepository.findById(id);
		if(opt.isPresent()) {
			Payment payment = opt.get();
			payment.setStatus(status);
			return paymentRepository.save(payment);
		}
		else {
			throw new IdNotFoundException("Id "+id+" is not available");
		}
	}
	
	public List<Payment> getPaymentGreaterThan(Double amount){
		List<Payment> payment = paymentRepository.findByAmountGreaterThan(amount);
		if(! payment.isEmpty()) {
			return payment;
		}
		else {
			throw new NoRecordAvailableException("Payment Details not available for amount greater than "+amount);
		}
	}
	
	public Page<Payment> getPaymentByPaginationAndSorting(int pageNumber, int pageSize, String field){
		if (pageNumber < 0 || pageSize <= 0) {
			throw new InvalidPaginationRequestException("Page number, page size and sort field must be provided and valid.");
		}
		Page<Payment> payments = paymentRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
		if(! payments.isEmpty()) {
			return payments;
		}
		else {
			throw new PaymentNotFoundException("No passenger available with field "+field);
		}
	}
}
