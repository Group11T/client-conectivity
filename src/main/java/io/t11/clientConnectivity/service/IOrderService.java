package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.dto.OrderDto;
import io.t11.clientConnectivity.model.Order;
import io.t11.clientConnectivity.model.Stock;
import io.t11.clientConnectivity.model.User;

import java.util.List;

public interface IOrderService {

    Order createNewOrder(OrderDto orderDto, User user);

    void cancelOrder(Stock stock);

    Stock getStock(String uniqueId);

    Stock trackStockStatus(Stock stock);

    List<Order> getAllOpenTradesOnMarket();

    List<Stock> getAllOpenTradesForUser(User user);

    List<Order> getAllOrders(User user);
}
