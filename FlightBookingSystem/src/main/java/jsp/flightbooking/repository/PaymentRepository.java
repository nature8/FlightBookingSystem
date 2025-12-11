package jsp.flightbooking.repository;

import jsp.flightbooking.entity.Status;
import jsp.flightbooking.entity.TransactionMode;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.flightbooking.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	List<Payment> findByStatus(Status status);
	List<Payment> findByModeOfTransaction(TransactionMode mode);
	Payment findByBookingId(Integer bookingId);
	List<Payment> findByAmountGreaterThan(Double amount);
}
