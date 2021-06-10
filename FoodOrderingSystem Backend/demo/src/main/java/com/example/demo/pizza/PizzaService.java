package com.example.demo.pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService (PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAllPizza(){
        return pizzaRepository.findAll();
    }

    public Pizza getPizza(Long id){
        return pizzaRepository.findById(id).orElse(null);
    }
}
