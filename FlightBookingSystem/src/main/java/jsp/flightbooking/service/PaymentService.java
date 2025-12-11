package jsp.flightbooking.service;

import java.util.List;

import jsp.flightbooking.entity.Status;
import jsp.flightbooking.entity.TransactionMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.flightbooking.dao.PaymentDao;
import jsp.flightbooking.dto.ResponseStructure;
import jsp.flightbooking.entity.Booking;
import jsp.flightbooking.entity.Payment;

@Service
public class PaymentService {
	@Autowired
	private PaymentDao paymentDao;
	
	public ResponseEntity<ResponseStructure<Payment>> recordPayment(Payment payment){
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		response.setStatuscode(HttpStatus.CREATED.value());
		response.setMessage("Payment records saved");
		response.setData(paymentDao.recordPayment(payment));
		return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.CREATED) ;
	}
	
	public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayment(){
		ResponseStructure<List<Payment>> response = new ResponseStructure<List<Payment>>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Payment records fetched");
		response.setData(paymentDao.getAllPayment());
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Payment>> getPaymentById(Integer id){
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Payment record fetched");
		response.setData(paymentDao.getPaymentById(id));
		return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByStatus(Status status){
		ResponseStructure<List<Payment>> response = new ResponseStructure<List<Payment>>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Payment records fetched");
		response.setData(paymentDao.getPaymentByStatus(status));
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByModeOfTransaction(TransactionMode mode){
		ResponseStructure<List<Payment>> response = new ResponseStructure<List<Payment>>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Payment records fetched");
		response.setData(paymentDao.getPaymentByModeOfTransaction(mode));
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Payment>> getPaymentByBookingId(Integer bookingId){
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Payment record fetched");
		response.setData(paymentDao.getPaymentByBookingId(bookingId));
		return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.OK);
	}
	
	
	
	public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(Integer id, Status status){
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Payment status updated");
		response.setData(paymentDao.updatePaymentStatus(id, status));
		return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentGreaterThan(Double amount){
		ResponseStructure<List<Payment>> response = new ResponseStructure<List<Payment>>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Payment records fetched");
		response.setData(paymentDao.getPaymentGreaterThan(amount));
		return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Page<Payment>>> getPaymentByPaginationAndSorting(int pageNumber, int pageSize, String field){
		ResponseStructure<Page<Payment>> response = new ResponseStructure<Page<Payment>>();
		response.setStatuscode(HttpStatus.OK.value());
		response.setMessage("Payment records fetched");
		response.setData(paymentDao.getPaymentByPaginationAndSorting(pageNumber, pageSize, field));
		return new ResponseEntity<ResponseStructure<Page<Payment>>>(response, HttpStatus.OK);
	}
}
