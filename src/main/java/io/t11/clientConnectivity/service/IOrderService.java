package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.dto.OrderDto;
import io.t11.clientConnectivity.model.CreatedOrder;
import io.t11.clientConnectivity.model.User;

public interface IOrderService {

    CreatedOrder createNewOrder(OrderDto orderDto);

//    CreatedOrder addCreatedOrderToPortfolio(CreatedOrder createdOrder, User user);

}
