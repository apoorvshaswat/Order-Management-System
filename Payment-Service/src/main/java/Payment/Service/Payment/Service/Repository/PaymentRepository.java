package Payment.Service.Payment.Service.Repository;

import Payment.Service.Payment.Service.Model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<Payment, String> {
}
