package io.t11.clientConnectivity.dao;

import io.t11.clientConnectivity.model.Order;
import io.t11.clientConnectivity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    Order findByUniqueId(String uniqueId);

    Order findByUser(User user);

    List<Order> findAllByUser(User user);

}
