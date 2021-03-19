package io.t11.clientConnectivity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private List<String> listOfShares = new ArrayList<>();
    private String portfolioName;

    public Portfolio() { }

    public Portfolio(List<String> listOfShares, String portfolioName) {
        this.listOfShares = listOfShares;
        this.portfolioName = portfolioName;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public List<String> getListOfShares() {
        return listOfShares;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    // Setters
//    public void setId(Long id) {
//        this.id = id;
//    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public void setListOfShares(List<String> listOfShares) {
        this.listOfShares = listOfShares;
    }
}
