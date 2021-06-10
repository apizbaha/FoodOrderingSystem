package com.example.demo.cart;


import com.example.demo.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService service){
        cartService = service;
    }

    @GetMapping("/all")
    public List<Cart> getAllCart(){
        return cartService.getAllCart();
    }

    @GetMapping("{id}")
    public Order getOrder(@PathVariable ("id") Long id){
        return cartService.getOrder(id);
    }

    @PostMapping("/add")
    public Long addCart(@RequestBody Cart cart){
        return cartService.addCart(cart);
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable ("id") Long id){
        cartService.deleteOrder(id);
    }
}
