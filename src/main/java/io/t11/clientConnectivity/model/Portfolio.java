package io.t11.clientConnectivity.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="portfolios")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private List<Order> listOfShares = new ArrayList<>();

    private String portfolioName;

    public Portfolio(String name) {
        this.portfolioName = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portfolio portfolio = (Portfolio) o;
        return Objects.equals(id, portfolio.id) &&
                Objects.equals(listOfShares, portfolio.listOfShares) &&
                Objects.equals(portfolioName, portfolio.portfolioName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, listOfShares, portfolioName);
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "id=" + id +
                ", listOfShares=" + listOfShares +
                ", portfolioName='" + portfolioName + '\'' +
                '}';
    }
}
