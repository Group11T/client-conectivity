package io.t11.clientConnectivity.dto;

import java.util.List;

public class PortfolioDto {

    private List<String> listOfShares;
    private String portfolioName;

    @Override
    public String toString() {
        return "PortfolioDto{" +
                "listOfShares=" + listOfShares +
                ", portfolioName='" + portfolioName + '\'' +
                '}';
    }

    public List<String> getListOfShares() {
        return listOfShares;
    }

    public void setListOfShares(List<String> listOfShares) {
        this.listOfShares = listOfShares;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
}
