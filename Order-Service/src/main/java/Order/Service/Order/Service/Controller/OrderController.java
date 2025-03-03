package Order.Service.Order.Service.Controller;

import Order.Service.Order.Service.Model.Order;
import Order.Service.Order.Service.Repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public OrderController(OrderRepository orderRepository, KafkaTemplate<String, Order> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public Mono<Order> createOrder(@RequestBody Order order) {
        return orderRepository.save(order)
                .flatMap(savedOrder -> {
                    kafkaTemplate.send("dynamic-order-topic", savedOrder);
                    return Mono.just(savedOrder);
                });
    }

    @GetMapping("/{id}")
    public Mono<Order> getOrder(@PathVariable String id) {
        return orderRepository.findById(id);
    }
}
