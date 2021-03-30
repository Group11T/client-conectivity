package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.model.Portfolio;
import io.t11.clientConnectivity.model.Stock;
import io.t11.clientConnectivity.model.User;

import java.util.List;

public interface IPortfolioService {

    Portfolio addPortfolio(User user,String ticker);

    List<Portfolio> addStockToPortfolio(Stock stock, User user);

    void closePortfolio(String sticker, User user);
}
