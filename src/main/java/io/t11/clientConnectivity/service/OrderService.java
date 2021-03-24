package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.dao.CreatedOrderRepository;
import io.t11.clientConnectivity.dto.OrderDto;
import io.t11.clientConnectivity.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    @Autowired
    CreatedOrderRepository orderRepository;

    private final String defaultValidityStatus="not validated";
    public OrderService(CreatedOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createNewOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setProduct(orderDto.getProduct());
        order.setQuantity(orderDto.getQuantity());
        order.setPrice(orderDto.getPrice());
        order.setSide(orderDto.getSide());
        order.setValidationStatus(defaultValidityStatus);
        return orderRepository.save(order);
    }

}
