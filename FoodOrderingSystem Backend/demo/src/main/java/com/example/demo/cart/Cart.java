package com.example.demo.cart;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private Long pizza_id;
    private double total;
    private Date est_time;
    private String phone;

    public Cart() {
    }

    public Cart(Long pizza_id, double total, Date est_time, String phone) {
        this.pizza_id = pizza_id;
        this.total = total;
        this.est_time = est_time;
        this.phone = phone;
    }

    public Cart(Long pizza_id, double total, String phone) {
        this.pizza_id = pizza_id;
        this.total = total;
        this.phone = phone;
        this.est_time = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPizza_id() {
        return pizza_id;
    }

    public void setPizza_id(Long pizza_id) {
        this.pizza_id = pizza_id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getEst_time() {
        return est_time;
    }

    public void setEst_time(Date est_time) {
        this.est_time = est_time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
