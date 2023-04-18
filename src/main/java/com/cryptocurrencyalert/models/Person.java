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

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "email не должен быть пустым")
    @Email
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Тикер не должен быть пустым")
    @Size(min = 2, max = 10, message = "Тикер должен быть от 2 до 10 символов длиной")
    @Column(name = "ticker")
    private String ticker;

    @NotEmpty(message = "Цена оповещения не должна быть пустой")
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
