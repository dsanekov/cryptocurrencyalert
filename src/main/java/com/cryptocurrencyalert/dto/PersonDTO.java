package com.cryptocurrencyalert.dto;


public class PersonDTO {

    private String name;

    private String email;

    private String ticker;

    private float alertPrice;

    private String condition;
    public PersonDTO(){}

    public PersonDTO(String name, String email, String ticker, float alertPrice, String condition) {
        this.name = name;
        this.email = email;
        this.ticker = ticker;
        this.alertPrice = alertPrice;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public float getAlertPrice() {
        return alertPrice;
    }

    public void setAlertPrice(float alertPrice) {
        this.alertPrice = alertPrice;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", ticker='" + ticker + '\'' +
                ", alertPrice=" + alertPrice +
                ", condition='" + condition + '\'' +
                '}';
    }
}
