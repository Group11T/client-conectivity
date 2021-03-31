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

    private String ticker;

    private int stockQuantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", stockQuantity='" + stockQuantity + '\'' +
                ", user=" + user +
                '}';
    }
}
