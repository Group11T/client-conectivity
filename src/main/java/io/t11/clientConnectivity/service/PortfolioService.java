package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.dao.PortfolioRepository;
import io.t11.clientConnectivity.dto.PortfolioDto;
import io.t11.clientConnectivity.model.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PortfolioService {

    @Autowired
    PortfolioRepository portfolioRepository;

    public Portfolio createNewPortfolio(PortfolioDto portfolioDto){
        Portfolio portfolio = new Portfolio("port1");
        portfolio.setPortfolioName(portfolioDto.getPortfolioName());
        portfolio.setListOfShares(portfolioDto.getListOfShares());
        return portfolioRepository.save(portfolio);
    }

}

