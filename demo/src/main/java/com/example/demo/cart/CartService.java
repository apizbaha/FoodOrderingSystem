package com.example.demo.cart;

import com.example.demo.order.Order;
import com.example.demo.pizza.Pizza;
import com.example.demo.pizza.PizzaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final PizzaRepository pizzaRepository;

    @Autowired
    public CartService(CartRepository cartRepository, PizzaRepository pizzaRepository){
        this.cartRepository = cartRepository;
        this.pizzaRepository = pizzaRepository;
    }

    public List<Cart> getAllCart(){
        return cartRepository.findAll();
    }

    public Order getOrder(Long id){
        Cart cart = cartRepository.findById(id).orElse(
                null
        );
        Pizza pizza =
        pizzaRepository.findById(cart.getPizza_id()).orElse(
                null
        );

        if(cart == null || pizza == null){
            throw new IllegalStateException("ID is not valid");
        }

        return new Order(cart, pizza);
    }

    public void deleteOrder(Long id){
        cartRepository.deleteById(id);
    }

    public Long addCart(Cart cart){
        Cart saved = cartRepository.save(cart);
        System.out.println("Cart ID: "+ saved.getId());
        return saved.getId();
    }
}
