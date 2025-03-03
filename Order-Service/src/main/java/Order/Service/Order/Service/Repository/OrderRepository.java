package Order.Service.Order.Service.Repository;

import Order.Service.Order.Service.Model.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
    Mono<Order> findById(String id);
}
