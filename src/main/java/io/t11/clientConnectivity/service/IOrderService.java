package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.dto.OrderDto;
import io.t11.clientConnectivity.model.Order;

public interface IOrderService {

    Order createNewOrder(OrderDto orderDto);

//    CreatedOrder addCreatedOrderToPortfolio(CreatedOrder createdOrder, User user);

}
