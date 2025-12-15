package com.iagobc.cursoSpringBoot.domain;

public class Discount {
    private Integer percentage;

    public Discount () {}

    public Discount(Integer percentage) {
        this.percentage = percentage;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
