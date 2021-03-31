package io.t11.clientConnectivity.controller;

import io.t11.clientConnectivity.model.Order;
import io.t11.clientConnectivity.model.Portfolio;
import io.t11.clientConnectivity.model.User;
import io.t11.clientConnectivity.service.OrderService;
import io.t11.clientConnectivity.service.PortfolioService;
import io.t11.clientConnectivity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("portfolio")
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    public PortfolioService portfolioService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public Portfolio addPortfolio(@RequestBody Order order){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final User user = userService.findUserByEmail(userDetails.getUsername());

        return portfolioService.addPortfolio(user,order.getProduct());
    }

    @PostMapping("/add/")
    public ResponseEntity<List<Portfolio>> addStockToPortfolio(@RequestParam String orderId){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final User user = userService.findUserByEmail(userDetails.getUsername());

        Order order = orderService.getOrder(orderId);
        List<Portfolio> portfolios = portfolioService.addStockToPortfolio(order,user);
        return ResponseEntity.ok().body(portfolios);
    }

    @DeleteMapping("/close")
    public void closePorfolio(@RequestParam  String ticker){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final User user = userService.findUserByEmail(userDetails.getUsername());

        portfolioService.closePortfolio(ticker,user);
    }
}
