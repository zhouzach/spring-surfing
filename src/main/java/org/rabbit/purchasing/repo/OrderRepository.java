package org.rabbit.purchasing.repo;

import org.rabbit.purchasing.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
