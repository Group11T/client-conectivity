package io.t11.clientConnectivity.controller;

import io.t11.clientConnectivity.dto.OrderDto;
import io.t11.clientConnectivity.model.CreatedOrder;
import io.t11.clientConnectivity.model.User;
import io.t11.clientConnectivity.service.IOrderService;
import io.t11.clientConnectivity.service.OrderClient;
import io.t11.validatingorders.wsdl.ValidateOrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class RestOrderController {
    private static Logger logger = LoggerFactory.getLogger((RestOrderController.class));

    @Autowired
    OrderClient orderClient;

    @Autowired
    IOrderService orderService;

    public RestOrderController(OrderClient orderClient, IOrderService orderService) {
        this.orderClient = orderClient;
        this.orderService = orderService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ValidateOrderResponse> createNewOrder(@RequestBody OrderDto orderDto){
        logger.info("saving new order");
        CreatedOrder createdOrder = orderService.createNewOrder(orderDto);

        //get Contextholder here
        User user = new User();

        logger.info("sending order to order_validation_service for validation ");
        ValidateOrderResponse validateOrderResponse=orderClient.validateNewOrder(createdOrder,user);
        return ResponseEntity.ok().body(validateOrderResponse);
    }
}


