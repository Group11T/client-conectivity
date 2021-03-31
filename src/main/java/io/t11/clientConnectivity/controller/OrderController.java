package io.t11.clientConnectivity.controller;

import io.t11.clientConnectivity.dto.OrderDto;
import io.t11.clientConnectivity.model.Order;
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
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<String> createNewOrder(@RequestBody OrderDto orderDto){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final User user = userService.findUserByEmail(userDetails.getUsername());
        double totalAmount = orderDto.getPrice() * orderDto.getQuantity();
        System.out.println(totalAmount);
        System.out.println(user.getBalance());
        if(user.getBalance() >= totalAmount){
            logger.info("saving new order");
            Order order = orderService.createNewOrder(orderDto,user);

            logger.info("sending order to order_validation_service for validation ");
            ValidateOrderResponse validateOrderResponse = orderClient.validateNewOrder(order,user);
            if(validateOrderResponse.getStatus().equals("Order successful")){
                double amount = orderDto.getPrice() * orderDto.getQuantity();
                userService.subtractFromUserBalance(user,amount);
            };
            return ResponseEntity.ok().body(validateOrderResponse.getStatus());
        }
        else{
            return ResponseEntity.badRequest().body("Your balance is insufficient to make a trade");
        }
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final User user = userService.findUserByEmail(userDetails.getUsername());

        return orderService.getAllOrders(user);
    }

    @GetMapping("/orders/market")
    public List<Order> getAllOpenOrdersOnMarket(){
        return orderService.getAllOpenTradesOnMarket();
    }

    @GetMapping("/orders/user")
    public List<Order> getAllOpenOrdersForUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final User user = userService.findUserByEmail(userDetails.getUsername());

        return orderService.getAllOpenTradesForUser(user);
    }

    @GetMapping("/orders/status/")
    public Order trackStockStatus(@RequestParam String uid){
       return orderService.trackOrderStatus(orderService.getOrder(uid));
    }

    @DeleteMapping("/orders/cancel/")
    public void cancelOrder(@RequestParam String uniqueId){
        Order order = orderService.getOrder(uniqueId);
        orderService.cancelOrder(order);
    }

}


