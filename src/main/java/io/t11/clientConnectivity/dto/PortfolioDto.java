package io.t11.clientConnectivity.dto;

import io.t11.clientConnectivity.model.Order;

import java.util.List;

public class PortfolioDto {

    private List<Order> listOfShares;
    private String portfolioName;

    @Override
    public String toString() {
        return "PortfolioDto{" +
                "listOfShares=" + listOfShares +
                ", portfolioName='" + portfolioName + '\'' +
                '}';
    }

    public List<Order> getListOfShares() {
        return listOfShares;
    }

    public void setListOfShares(List<Order> listOfShares) {
        this.listOfShares = listOfShares;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
}
