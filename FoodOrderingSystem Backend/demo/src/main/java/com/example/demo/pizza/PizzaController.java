package com.example.demo.pizza;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/pizza")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService service){
        pizzaService = service;
    }

    @GetMapping("/all")
    public List<Pizza> getAllPizza(){
        return pizzaService.getAllPizza();
    }

    @GetMapping("{id}")
    public Pizza getPizza(@PathVariable("id") Long id){
        return pizzaService.getPizza(id);
    }
}
