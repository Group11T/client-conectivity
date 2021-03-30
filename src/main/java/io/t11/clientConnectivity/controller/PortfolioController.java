package io.t11.clientConnectivity.controller;

import io.t11.clientConnectivity.model.Portfolio;
import io.t11.clientConnectivity.dto.PortfolioDto;
import io.t11.clientConnectivity.model.Stock;
import io.t11.clientConnectivity.model.User;
import io.t11.clientConnectivity.service.PortfolioService;
import io.t11.clientConnectivity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("portfolio")
@RequestMapping("/portfolio")
public class PortfolioController {

    @Autowired
    public PortfolioService portfolioService;

    @Autowired
    UserService userService;

    @PostMapping("/create/{userId}")
    public Portfolio addPortfolio(@RequestBody Stock stock,@PathVariable Long userId){
        User user = userService.getUserById(userId).get();
        return portfolioService.addPortfolio(user,stock.getTicker());
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<List<Portfolio>> addStockToPortfolio(@RequestBody Stock stock, @PathVariable Long userId){
        User user = userService.getUserById(userId).get();
        List<Portfolio> portfolios = portfolioService.addStockToPortfolio(stock,user);
        return ResponseEntity.ok().body(portfolios);
    }

    @DeleteMapping("/close")
    public void closePorfolio(@PathVariable  String ticker){
        final User user = userService.findUserByEmail(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmailAddress());
        portfolioService.closePortfolio(ticker,user);
    }
}
