package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.dto.OrderDto;
import io.t11.clientConnectivity.model.CreatedOrder;

public interface IOrderService {

    CreatedOrder createNewOrder(OrderDto orderDto);

}
