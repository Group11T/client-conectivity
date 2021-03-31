package io.t11.clientConnectivity.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.t11.clientConnectivity.controller.OrderController;
import io.t11.clientConnectivity.dao.OrderRepository;
import io.t11.clientConnectivity.dto.OrderDto;
import io.t11.clientConnectivity.model.Order;
import io.t11.clientConnectivity.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository orderRepository;

    RestTemplate restTemplate = new RestTemplate();
    private static Logger logger = LoggerFactory.getLogger((OrderService.class));
    private final String defaultValidityStatus="not validated";
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createNewOrder(OrderDto orderDto, User user) {
        Order order = new Order();
        order.setProduct(orderDto.getProduct());
        order.setQuantity(orderDto.getQuantity());
        order.setPrice(orderDto.getPrice());
        order.setSide(orderDto.getSide());
        order.setValidationStatus(defaultValidityStatus);
        order.setUser(user);
        order.setCumulativeQuantity(0);
        order.setUniqueId(null);
        order.setExchangeTradedOn(null);
        return orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Order order) {
        final String url =order.getExchangeTradedOn()+"order/" + order.getUniqueId();
        restTemplate.delete(url);
    }

    @Override
    public List<Order> getAllOpenTradesOnMarket() {
        String url = "https://exchange.matraining.com/md/";
        List<Object> marketData = null;
        try {
            marketData = restTemplate.getForObject(url, ArrayList.class);
        } catch (Throwable t) {
            logger.error("Error handling network call",t);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List marketDataList = marketData.stream()
                .map(item->objectMapper.convertValue(item, Order.class))
                .filter(item->item.getCumulativeQuantity() < item.getQuantity())
                .collect(Collectors.toList());
        return marketDataList;
    }

    @Override
    public List<Order> getAllOpenTradesForUser(User user) {
        List<Order> stockList = orderRepository.findAllByUser(user);
        List<Order> openStocks = stockList.stream()
                .filter(stock -> stock.getCumulativeQuantity() <stock.getQuantity())
                .collect(Collectors.toList());
        return openStocks;
    }

    @Override
    public List<Order> getAllOrders(User user) {
        return orderRepository.findAllByUser(user);
    }

    @Override
    public Order getOrder(String uniqueId){
       return orderRepository.findByUniqueId(uniqueId);
    }

    @Override
    public Order trackOrderStatus(Order order) {
        String url = order.getExchangeTradedOn() + "orders/" + order.getUniqueId();
        Object item = restTemplate.getForObject(url,Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Order stockItem = objectMapper.convertValue(item, Order.class);
        return stockItem;
    }

}
