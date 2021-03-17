package io.t11.clientConnectivity.controller;

import io.t11.clientConnectivity.Portfolio;
import io.t11.clientConnectivity.dto.PortfolioDto;
import io.t11.clientConnectivity.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortfolioController {

    @Autowired
    public PortfolioService portfolioService;

    @PostMapping()
    public Portfolio createNemPortfolio(PortfolioDto portfolioDto) {

        return portfolioService.createNewPortfolio(portfolioDto);
    }
}
