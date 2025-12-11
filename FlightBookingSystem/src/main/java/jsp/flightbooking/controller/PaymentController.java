package jsp.flightbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.flightbooking.dto.ResponseStructure;
import jsp.flightbooking.entity.Payment;
import jsp.flightbooking.entity.Status;
import jsp.flightbooking.entity.TransactionMode;
import jsp.flightbooking.service.PaymentService;

@RequestMapping("/payment")
@RestController
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Payment>> createPayment(Payment payment){
		return paymentService.recordPayment(payment);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayment(){
		return paymentService.getAllPayment();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentById(@PathVariable Integer id){
		return paymentService.getPaymentById(id);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByStatus(@PathVariable Status status){
		return paymentService.getPaymentByStatus(status);
	}
	
	@GetMapping("/modes/{mode}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByModeOfTransaction(@PathVariable TransactionMode mode){
		return paymentService.getPaymentByModeOfTransaction(mode);
	}
	
	@GetMapping("/booking/{bookingId}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentByBookingId(@PathVariable Integer bookingId){
		return paymentService.getPaymentByBookingId(bookingId);
	}
	
	@PutMapping("/{id}/{status}")
	public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(@PathVariable Integer id, @PathVariable Status status){
		return paymentService.updatePaymentStatus(id, status);
	}
	
	@GetMapping("/greater/{amount}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentGreaterThan(@PathVariable Double amount){
		return paymentService.getPaymentGreaterThan(amount);
	}
	
	@GetMapping("/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Payment>>> getPaymentByPaginationAndSorting(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field){
		return paymentService.getPaymentByPaginationAndSorting(pageNumber, pageSize, field);
	}
}

