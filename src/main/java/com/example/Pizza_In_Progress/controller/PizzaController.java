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
import java.util.Optional;

@RestController
public class PizzaController {


    private PizzaRepository pizzaRepository;
//    @Autowired
//    public PizzaController(PizzaRepository pizzaRepository) {
//        this.pizzaRepository = pizzaRepository;
//
//    }


    private CafeRepository cafeRepository;
    @Autowired
    public PizzaController(CafeRepository cafeRepository, PizzaRepository pizzaRepository) {
        this.cafeRepository = cafeRepository;
        this.pizzaRepository = pizzaRepository;
    }



    @PostMapping("/pizza")
    public ResponseEntity<Pizza> createNewPizza(
            @RequestBody Pizza pizza
    ){
        Pizza newPizza = pizzaRepository.save(pizza);
        return new ResponseEntity<>(newPizza, HttpStatus.CREATED);
    }



//    @PostMapping("/cafe/{cafe_id}/pizza")
//    public ResponseEntity<Pizza> createPizzaForACafe(
//            @PathVariable Integer cafe_id,
//            @RequestBody Pizza pizza
//    )
//    {
//        Pizza result = null;
//        Cafe cafe = cafeRepository.findById(cafe_id).orElse(null);
//        if (cafe != null && pizza != null){
//            pizza.setCafe(cafe);
//            result = pizzaRepository.save(pizza);
//
//        }
//        return new ResponseEntity<>(result, HttpStatus.CREATED);
//
//    }




    @GetMapping("/pizza")
    public ResponseEntity<List<Pizza>> getAllPizza()
    {
        return new ResponseEntity<>(pizzaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/pizza/cafe_id")
    public ResponseEntity<List<Pizza>> getPizzaByCafeId(
            @RequestParam(name = "cafe_id") Integer cafe_id
    )
    {
        Cafe cafe = cafeRepository.findById(cafe_id).orElse(null);
        return ResponseEntity.ok(pizzaRepository.findByCafe(cafe));
    }

    @GetMapping("/pizza/{pizza_id}")
    public ResponseEntity<Optional<Pizza>> getPizzaById(
            @PathVariable Integer pizza_id
    )
    {
        return ResponseEntity.ok(pizzaRepository.findById(pizza_id));
    }

//    @GetMapping("/pizza/cafe_id")
//    public ResponseEntity<Optional<Pizza>> findPizzaByCafeId(
//            @RequestParam(name = "id") Integer id
//    ){
//        return new ResponseEntity<>(
//                pizzaRepository.findById(id), HttpStatus.OK
//        );
//    }

    @GetMapping("/pizza/name")
    public ResponseEntity<Pizza> findPizzaByName(
            @RequestParam(name = "pizza_name") String pizza_name
    ){
        return new ResponseEntity<>(
                pizzaRepository.findByPizzaName(pizza_name), HttpStatus.OK
        );
    }

    @PutMapping("/pizza/{id}")
    public ResponseEntity<Pizza> updatePizzaById(
            @PathVariable Integer id,
            @RequestBody Pizza pizzaRequest
    )
    {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        if (pizza != null && pizzaRequest != null)
        {
            pizza.setPizzaName(pizzaRequest.getPizzaName());
            pizza = pizzaRepository.save(pizza);
        }
        return ResponseEntity.ok(pizza);
    }

    @DeleteMapping("/pizza/{pizza_id}")
    public ResponseEntity<Pizza> deletePizzaById(
            @PathVariable Integer pizza_id
    ){
        pizzaRepository.deleteById(pizza_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
