package Payment.Service.Payment.Service.KafkaListener;

import Payment.Service.Payment.Service.Model.Order;
import Payment.Service.Payment.Service.Model.Payment;
import Payment.Service.Payment.Service.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class PaymentListener {

    private static final Logger logger = LoggerFactory.getLogger(PaymentListener.class);

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentListener(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @KafkaListener(topics = "dynamic-order-topic", groupId = "payment-service", containerFactory = "kafkaListenerContainerFactory")
    public void handleOrderCreatedEvent(Order order) {

            String userId = order.getUserId();
            List<String> productIds = order.getProductIds();

            String paymentStatus = simulatePaymentProcessing();
            logger.info("Payment status for order {}: {}", order.getId(), paymentStatus);

            Payment payment = new Payment(order.getId(), paymentStatus, userId, productIds);
            paymentRepository.save(payment);
            logger.info("Payment saved for order {}: {}", order.getId(), paymentStatus);

    }

    private String simulatePaymentProcessing() {

        return (Math.random() < 0.9) ? "PAID" : "FAILED";
    }
}
