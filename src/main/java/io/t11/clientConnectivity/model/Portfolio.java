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
    //private List<String> listOfShares = new ArrayList<>();
    private String portfolioName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public List<String> getListOfShares() {
//        return listOfShares;
//    }

//    public void setListOfShares(List<String> listOfShares) {
//        this.listOfShares = listOfShares;
//    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
}
