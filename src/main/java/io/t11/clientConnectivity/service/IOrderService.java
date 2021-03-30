package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.dto.OrderDto;
import io.t11.clientConnectivity.model.Order;
import io.t11.clientConnectivity.model.User;

import java.util.List;

public interface IOrderService {

    Order createNewOrder(OrderDto orderDto, User user);

    void cancelOrder(Order order);

    Order getOrder(String uniqueId);

    Order trackOrderStatus(Order stock);

    List<Order> getAllOpenTradesOnMarket();

    List<Order> getAllOpenTradesForUser(User user);

    List<Order> getAllOrders(User user);
}
