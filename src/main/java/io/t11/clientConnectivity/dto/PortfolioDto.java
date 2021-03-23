package io.t11.clientConnectivity.dto;

import io.t11.clientConnectivity.model.CreatedOrder;

import java.util.List;

public class PortfolioDto {

    private List<CreatedOrder> listOfShares;
    private String portfolioName;

    @Override
    public String toString() {
        return "PortfolioDto{" +
                "listOfShares=" + listOfShares +
                ", portfolioName='" + portfolioName + '\'' +
                '}';
    }

    public List<CreatedOrder> getListOfShares() {
        return listOfShares;
    }

    public void setListOfShares(List<CreatedOrder> listOfShares) {
        this.listOfShares = listOfShares;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
}
