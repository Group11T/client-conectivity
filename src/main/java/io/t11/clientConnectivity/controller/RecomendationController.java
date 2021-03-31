package io.t11.clientConnectivity.controller;

import io.t11.clientConnectivity.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RecomendationController {

    @Autowired
    RecommendationService recommendationService;

    public RecomendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/recommend/sell")
    public ResponseEntity<Map<Integer,Double>> getMaximumBid(@RequestParam String ticker, @RequestParam String exchange) throws Throwable {
        return ResponseEntity.ok().body(recommendationService.getMaximumBidPriceByTicker(ticker,exchange));
    }

    @GetMapping("/recommend/buy")
    public ResponseEntity<Map<Integer,Double>> getMinimumAskOrder(@RequestParam String ticker,@RequestParam String exchange) throws Throwable {
        return ResponseEntity.ok().body(recommendationService.getMimimumAskPriceByTicker(ticker,exchange));
    }
}
