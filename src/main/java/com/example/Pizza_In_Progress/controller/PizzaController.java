package com.example.Pizza_In_Progress.controller;

import com.example.Pizza_In_Progress.entity.Cafe;
import com.example.Pizza_In_Progress.entity.Pizza;
import com.example.Pizza_In_Progress.repository.CafeRepository;
import com.example.Pizza_In_Progress.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaController {


    private PizzaRepository pizzaRepository;
    @Autowired
    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;

    }


    private CafeRepository cafeRepository;
    @Autowired
    public PizzaController(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @PostMapping("/pizza")
    public ResponseEntity<Pizza> createNewPizza(
            @RequestBody Pizza pizza
    ){
        return new ResponseEntity<>(pizza, HttpStatus.CREATED);
    }



    @PostMapping("/cafe/{cafe_id}/pizza")
    public ResponseEntity<Pizza> createPizzaForACafe(
            @PathVariable Integer cafe_id,
            @RequestBody Pizza pizza
    )
    {
        Pizza result = null;
        Cafe cafe = cafeRepository.findById(cafe_id).orElse(null);
        if (cafe != null && pizza != null){
            pizza.setCafe(cafe);
            result = pizzaRepository.save(pizza);

        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);

    }




    @GetMapping("/pizza")
    public ResponseEntity<List<Pizza>> getAllPizza()
    {
        return new ResponseEntity<>(pizzaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/pizza/{cafe_id}")
    public ResponseEntity<List<Pizza>> getPizzaByCafeId(
            @RequestParam (name = "cafe_id") Integer cafe_id
    )
    {
        Cafe cafe = cafeRepository.findById(cafe_id).orElse(null);
        return  ResponseEntity.ok(pizzaRepository.findByCafe(cafe));
    }
}
