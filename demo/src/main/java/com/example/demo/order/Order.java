package com.example.demo.order;

import com.example.demo.cart.Cart;
import com.example.demo.pizza.Pizza;

import javax.persistence.*;
import java.util.Date;

public class Order {
    private Cart cart;
    private Pizza pizza;

    public Order() {
    }

    public Order(Cart cart, Pizza pizza) {
        this.cart = cart;
        this.pizza = pizza;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}
