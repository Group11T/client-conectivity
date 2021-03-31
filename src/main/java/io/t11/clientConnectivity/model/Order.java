package io.t11.clientConnectivity.model;

import javax.persistence.*;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private String product;

    private int quantity;

    private double price;

    private String side;

    private String validationStatus;

    private String uniqueId;

    private int cumulativeQuantity;

    private String exchangeTradedOn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public int getCumulativeQuantity() {
        return cumulativeQuantity;
    }

    public void setCumulativeQuantity(int cumulativeQuantity) {
        this.cumulativeQuantity = cumulativeQuantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getExchangeTradedOn() {
        return exchangeTradedOn;
    }

    public void setExchangeTradedOn(String exchangeTradedOn) {
        this.exchangeTradedOn = exchangeTradedOn;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", side='" + side + '\'' +
                ", validationStatus='" + validationStatus + '\'' +
                ", uniqueId='" + uniqueId + '\'' +
                ", cumulativeQuantity=" + cumulativeQuantity +
                ", exchangeTradedOn='" + exchangeTradedOn + '\'' +
                ", user=" + user +
                '}';
    }
}
