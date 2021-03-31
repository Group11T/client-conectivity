package io.t11.clientConnectivity.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.t11.clientConnectivity.dto.OrderbookDto;
import io.t11.clientConnectivity.model.ExchangeDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class RecommendationService {

    RestTemplate restTemplate = new RestTemplate();

    public Map<Integer,Double> getMimimumAskPriceByTicker(String ticker, String exchange) throws Throwable {
        final String url = ExchangeDetails.valueOf(exchange.toUpperCase()).getUrl() + ticker + "/sell";
        ObjectMapper objectMapper = new ObjectMapper();

        List receivedOrderbook = restTemplate.getForObject(url, ArrayList.class);
        Object minimumAskPriceObject= receivedOrderbook.stream()
                .map(item-> objectMapper.convertValue(item, OrderbookDto.class))
                .filter(item->((OrderbookDto) item).getCumulatitiveQuantity() < ((OrderbookDto) item).getQuantity())
                .min(Comparator.comparing(OrderbookDto::getPrice))
                .get();

        OrderbookDto orderbookDto = objectMapper.convertValue(minimumAskPriceObject, OrderbookDto.class);
        Map<Integer,Double> response = new HashMap();
        response.put(orderbookDto.getQuantity(),orderbookDto.getPrice());
        return response;
    }


    public Map<Integer,Double> getMaximumBidPriceByTicker(String ticker,String exchange) throws Throwable {
        final String url = ExchangeDetails.valueOf(exchange.toUpperCase()).getUrl() + ticker + "/buy";
        ObjectMapper objectMapper = new ObjectMapper();

        List receivedOrderbook = restTemplate.getForObject(url,ArrayList.class);
        Object maximumAskPriceObject= receivedOrderbook.stream()
                .map(item-> objectMapper.convertValue(item, OrderbookDto.class))
                .filter(item->((OrderbookDto) item).getCumulatitiveQuantity() < ((OrderbookDto) item).getQuantity())
                .max(Comparator.comparing(OrderbookDto::getPrice))
                .get();

        OrderbookDto orderbookDto = objectMapper.convertValue(maximumAskPriceObject, OrderbookDto.class);
        Map<Integer,Double> response = new HashMap();
        response.put(orderbookDto.getQuantity(),orderbookDto.getPrice());
        return response;
    }
}
