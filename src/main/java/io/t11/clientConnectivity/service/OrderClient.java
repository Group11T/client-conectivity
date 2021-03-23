package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.model.CreatedOrder;
import io.t11.clientConnectivity.model.User;
import io.t11.validatingorders.wsdl.ValidateOrderRequest;
import io.t11.validatingorders.wsdl.ValidateOrderResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class OrderClient extends WebServiceGatewaySupport {

    public ValidateOrderResponse validateNewOrder(CreatedOrder createdOrder, User user){
        ValidateOrderRequest validateOrderRequest = new ValidateOrderRequest();
        validateOrderRequest.setOrderId(createdOrder.getId());
        validateOrderRequest.setProduct(createdOrder.getProduct());
        validateOrderRequest.setQuantity(createdOrder.getQuantity());
        validateOrderRequest.setPrice(createdOrder.getPrice());
        validateOrderRequest.setSide(createdOrder.getSide());
//        validateOrderRequest.setUserId(user.getId);
        ValidateOrderResponse validateOrderResponse=(ValidateOrderResponse)getWebServiceTemplate()
                .marshalSendAndReceive("https://trade-validation.herokuapp.com/ws/orders",validateOrderRequest,
                        new SoapActionCallback("http://www.group11.com/soap/api/order-validation/ValidateOrderRequest"));
        return validateOrderResponse;
    }
}
