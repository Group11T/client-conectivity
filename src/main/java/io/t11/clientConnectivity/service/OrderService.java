package io.t11.clientConnectivity.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.t11.clientConnectivity.dao.OrderRepository;
import io.t11.clientConnectivity.dao.StockRepository;
import io.t11.clientConnectivity.dto.OrderDto;
import io.t11.clientConnectivity.model.Order;
import io.t11.clientConnectivity.model.Stock;
import io.t11.clientConnectivity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    StockRepository stockRepository;

    RestTemplate restTemplate = new RestTemplate();

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
        return orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Stock stock) {
        final String url =stock.getExchange()+"order/" + stock.getUniqueId();
        restTemplate.delete(url);
    }

    @Override
    public List<Order> getAllOpenTradesOnMarket() {
        String url = "https://exchange.matraining.com/md/";
        List<Object> marketData = restTemplate.getForObject(url, ArrayList.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List marketDataList = marketData.stream()
                .map(item->objectMapper.convertValue(item, Stock.class))
                .filter(item->item.getCummlativeQuantity() < item.getQuantity())
                .collect(Collectors.toList());
        return marketDataList;
    }

    @Override
    public List<Stock> getAllOpenTradesForUser(User user) {
        List<Stock> stockList = stockRepository.findAllByUser(user);
        List<Stock> openStocks = stockList.stream()
                .filter(stock -> stock.getCummlativeQuantity() <stock.getQuantity())
                .collect(Collectors.toList());
        return openStocks;
    }

    @Override
    public List<Order> getAllOrders(User user) {
        return orderRepository.findAllByUser(user);
    }

    @Override
    public Stock getStock(String uniqueId){
       return stockRepository.findByUniqueId(uniqueId);
    }

    @Override
    public Stock trackStockStatus(Stock stock) {
        String url = stock.getExchange() + "orders/" + stock.getUniqueId();
        Object item = restTemplate.getForObject(url,Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Stock stockItem = objectMapper.convertValue(item, Stock.class);
        return stockItem;
    }

}
