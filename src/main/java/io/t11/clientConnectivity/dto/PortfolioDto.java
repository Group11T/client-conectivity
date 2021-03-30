package io.t11.clientConnectivity.dto;

public class PortfolioDto {

    private String ticker;
    private String stockQuantity;
    private Long userId;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(String stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PortfolioDto{" +
                "ticker='" + ticker + '\'' +
                ", stockQuantity='" + stockQuantity + '\'' +
                ", userId=" + userId +
                '}';
    }
}
