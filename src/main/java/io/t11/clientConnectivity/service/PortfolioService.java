package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.model.Portfolio;
import io.t11.clientConnectivity.dao.PortfolioRepository;
import io.t11.clientConnectivity.dto.PortfolioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {

    @Autowired
    PortfolioRepository portfolioRepository;

    public Portfolio createNewPortfolio(PortfolioDto portfolioDto){
        Portfolio portfolio = new Portfolio();
        portfolio.setPortfolioName(portfolioDto.getPortfolioName());
       // portfolio.setListOfShares(portfolioDto.getListOfShares());

    return portfolioRepository.save(portfolio);
    }


}
