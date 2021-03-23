package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.dao.CreatedOrderRepository;
import io.t11.clientConnectivity.dto.OrderDto;
import io.t11.clientConnectivity.model.CreatedOrder;
import io.t11.clientConnectivity.model.User;
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
    public CreatedOrder createNewOrder(OrderDto orderDto) {
        CreatedOrder createdOrder = new CreatedOrder();
        createdOrder.setProduct(orderDto.getProduct());
        createdOrder.setQuantity(orderDto.getQuantity());
        createdOrder.setPrice(orderDto.getPrice());
        createdOrder.setSide(orderDto.getSide());
        createdOrder.setValidationStatus(defaultValidityStatus);
        return orderRepository.save(createdOrder);
    }
//
//    @Override
//    public CreatedOrder addCreatedOrderToPortfolio(CreatedOrder createdOrder, User user) {
////        user.getPortfolio().add(createdOrder);
//        return new CreatedOrder();
//    }

}
