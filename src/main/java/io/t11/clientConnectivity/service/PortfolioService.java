package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.dao.PortfolioRepository;
import io.t11.clientConnectivity.model.Order;
import io.t11.clientConnectivity.model.Portfolio;
import io.t11.clientConnectivity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortfolioService implements IPortfolioService{

    @Autowired
    PortfolioRepository portfolioRepository;

    @Override
    public Portfolio addPortfolio(User user,String ticker) {
        List<Portfolio> portfolios = portfolioRepository.findAllByUser(user);
        Portfolio portfolio = null;

        boolean portfolioExists = portfolios.stream().anyMatch(portfolioToFind -> ticker.equals(portfolioToFind.getTicker()));
        if(!portfolioExists){
            portfolio = new Portfolio();
            portfolio.setTicker(ticker);
            portfolio.setStockQuantity(0);
            portfolio.setUser(user);
            portfolioRepository.save(portfolio);
        }
        return portfolio;
    }

    @Override
    public List<Portfolio> addStockToPortfolio(Order order, User user) {
        List<Portfolio> portfolios = portfolioRepository.findAllByUser(user);
        for (Portfolio portfolio: portfolios) {
            if(portfolio.getTicker().equals(order.getProduct())){
                portfolio.setStockQuantity(order.getCumulativeQuantity());
                portfolioRepository.save(portfolio);
            }
            else{
                addPortfolio(user,portfolio.getTicker());
            }
        }
        return portfolios;
    }

    @Override
    public void closePortfolio(String ticker, User user) {
        List<Portfolio> portfolios = portfolioRepository.findAllByUser(user);
        portfolios.stream()
                .filter(portfolio -> portfolio.getTicker().equals(ticker))
                .map(portfolio -> {
                    portfolioRepository.delete(portfolio);
                    return portfolios;
                }).collect(Collectors.toList());

    }
}

