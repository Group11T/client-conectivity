package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.dao.CreatedOrderRepository;
import io.t11.clientConnectivity.dto.OrderDto;
import io.t11.clientConnectivity.model.CreatedOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    @Autowired
    CreatedOrderRepository orderRepository;

    public OrderService(CreatedOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public CreatedOrder createNewOrder(OrderDto orderDto) {
        CreatedOrder createdOrder = new CreatedOrder();
        createdOrder.setProduct(orderDto.getProduct());
        createdOrder.setQuantity(orderDto.getQuantity());
        createdOrder.setPrice(orderDto.getPrice());
        createdOrder.setSide(orderDto.getSide());
        return orderRepository.save(createdOrder);
    }

}
