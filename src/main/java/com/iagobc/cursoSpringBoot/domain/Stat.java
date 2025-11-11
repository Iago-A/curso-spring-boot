package com.iagobc.cursoSpringBoot.domain;

public class Stat {

    private int totalCustomers;
    private int totalProducts;
    private Double averagePrice;

    public Stat(int totalCustomers, int totalProducts, Double averagePrice) {
        this.totalCustomers = totalCustomers;
        this.totalProducts = totalProducts;
        this.averagePrice = averagePrice;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }
}
