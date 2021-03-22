package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.model.CreatedOrder;
import io.t11.validatiingorders.wsdl.ValidateOrderRequest;
import io.t11.validatiingorders.wsdl.ValidateOrderResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class OrderClient extends WebServiceGatewaySupport {

    public ValidateOrderResponse validateNewOrder(CreatedOrder createdOrder,Long userId){

        ValidateOrderRequest validateOrderRequest = new ValidateOrderRequest();
        validateOrderRequest.setOrderId(createdOrder.getId());
        validateOrderRequest.setProduct(createdOrder.getProduct());
        validateOrderRequest.setQuantity(createdOrder.getQuantity());
        validateOrderRequest.setPrice(createdOrder.getPrice());
        validateOrderRequest.setSide(createdOrder.getSide());
        validateOrderRequest.setUserId(userId);

        ValidateOrderResponse validateOrderResponse=(ValidateOrderResponse)getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8040/ws/orders",validateOrderRequest,
                        new SoapActionCallback("http://www.group11.com/soap/api/createdOrder-validation/ValidateOrderRequest"));
        return validateOrderResponse;
    }
}
