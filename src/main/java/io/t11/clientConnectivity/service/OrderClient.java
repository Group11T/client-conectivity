package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.model.Order;
import io.t11.clientConnectivity.model.User;
import io.t11.validatingorders.wsdl.ValidateOrderRequest;
import io.t11.validatingorders.wsdl.ValidateOrderResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class OrderClient extends WebServiceGatewaySupport {

    public ValidateOrderResponse validateNewOrder(Order order, User user){
        ValidateOrderRequest validateOrderRequest = new ValidateOrderRequest();
        validateOrderRequest.setOrderId(order.getId());
        validateOrderRequest.setProduct(order.getProduct());
        validateOrderRequest.setQuantity(order.getQuantity());
        validateOrderRequest.setPrice(order.getPrice());
        validateOrderRequest.setSide(order.getSide());
//        validateOrderRequest.setUserId(user.getId);
        ValidateOrderResponse validateOrderResponse=(ValidateOrderResponse)getWebServiceTemplate()
                .marshalSendAndReceive("https://trade-validation.herokuapp.com/ws/orders",validateOrderRequest,
                        new SoapActionCallback("http://www.group11.com/soap/api/order-validation/ValidateOrderRequest"));
        return validateOrderResponse;
    }
}
