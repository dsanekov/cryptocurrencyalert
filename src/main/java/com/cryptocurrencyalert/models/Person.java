package com.cryptocurrencyalert.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min = 0, max = 100, message = "Should be between 0 and 100 characters")
    private String name;

    @NotEmpty(message = "Should be not empty")
    @Column(name = "email")
    @Email(message = "Not valid email")
    private String email;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "alert_price")
    private float alertPrice;

    @NotEmpty
    private String condition;

    public Person() {
    }

    public Person(int id, String username, String email, String ticker, float alertPrice, String condition) {
        this.id = id;
        this.name = username;
        this.email = email;
        this.ticker = ticker;
        this.alertPrice = alertPrice;
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
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
        return "Person{" +
                "id=" + id +
                ", username='" + name + '\'' +
                ", email='" + email + '\'' +
                ", ticker='" + ticker + '\'' +
                ", alertPrice=" + alertPrice +
                ", condition='" + condition + '\'' +
                '}';
    }
}
