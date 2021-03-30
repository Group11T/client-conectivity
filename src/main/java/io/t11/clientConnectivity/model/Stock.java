package io.t11.clientConnectivity.model;

import javax.persistence.*;

@Entity
@Table(name="stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private String uniqueId;

    private String ticker;

    private int quantity;

    private int cummlativeQuantity;

    private String exchange;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCummlativeQuantity() {
        return cummlativeQuantity;
    }

    public void setCummlativeQuantity(int cummlativeQuantity) {
        this.cummlativeQuantity = cummlativeQuantity;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", uniqueId='" + uniqueId + '\'' +
                ", ticker='" + ticker + '\'' +
                ", quantity=" + quantity +
                ", cummlativeQuantity=" + cummlativeQuantity +
                ", exchange='" + exchange + '\'' +
                ", user=" + user +
                '}';
    }
}
