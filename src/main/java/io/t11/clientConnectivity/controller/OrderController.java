package io.t11.clientConnectivity.controller;

import io.t11.clientConnectivity.dto.OrderDto;
import io.t11.clientConnectivity.model.Order;
import io.t11.clientConnectivity.model.Stock;
import io.t11.clientConnectivity.model.User;
import io.t11.clientConnectivity.service.IOrderService;
import io.t11.clientConnectivity.service.OrderClient;
import io.t11.clientConnectivity.service.UserService;
import io.t11.validatingorders.wsdl.ValidateOrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger((OrderController.class));

    @Autowired
    OrderClient orderClient;

    @Autowired
    IOrderService orderService;

    @Autowired
    UserService userService;


    public OrderController(OrderClient orderClient, IOrderService orderService, UserService userService) {
        this.orderClient = orderClient;
        this.orderService = orderService;
        this.userService = userService;
    }

    RestTemplate restTemplate=new RestTemplate();

    @PostMapping("/orders/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ValidateOrderResponse> createNewOrder(@RequestBody OrderDto orderDto){
        logger.info("saving new order");
        final User user = userService.findUserByEmail(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmailAddress());
        Order order = orderService.createNewOrder(orderDto,user);

        logger.info("sending order to order_validation_service for validation ");
        ValidateOrderResponse validateOrderResponse=orderClient.validateNewOrder(order,user);
        return ResponseEntity.ok().body(validateOrderResponse);
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        final User user = userService.findUserByEmail(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmailAddress());
        return orderService.getAllOrders(user);
    }

    @GetMapping("/orders/market")
    public List<Order> getAllOpenOrdersOnMarket(){
        return orderService.getAllOpenTradesOnMarket();
    }

    @GetMapping("/user/stocks")
    public List<Stock> getAllOpenOrdersForUser(){
        final User user = userService.findUserByEmail(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmailAddress());
        return orderService.getAllOpenTradesForUser(user);
    }

    @GetMapping("/stock/status/{uid}")
    public Stock trackStockStatus(String uid){
       return orderService.trackStockStatus(orderService.getStock(uid));
    }

    @DeleteMapping("/stock/cancel/{uniqueId}")
    public void cancelOrder(String uniqueId){
        Stock stock = orderService.getStock(uniqueId);
        orderService.cancelOrder(stock);
    }

}


