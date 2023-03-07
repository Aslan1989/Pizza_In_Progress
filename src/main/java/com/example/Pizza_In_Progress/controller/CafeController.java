package com.example.Pizza_In_Progress.controller;

import com.example.Pizza_In_Progress.entity.Cafe;
import com.example.Pizza_In_Progress.repository.CafeRepository;
import com.example.Pizza_In_Progress.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CafeController {

    private  CafeRepository cafeRepository;
    private PizzaRepository pizzaRepository;
    @Autowired
    public CafeController(CafeRepository cafeRepository, PizzaRepository pizzaRepository) {
        this.cafeRepository = cafeRepository;
        this.pizzaRepository = pizzaRepository;
    }
    @PostMapping("/cafe")
    public ResponseEntity<Cafe> createCafe(
            @RequestBody Cafe cafeRequest
    )
    {
        cafeRequest = cafeRepository.save(cafeRequest);
        return new ResponseEntity<>(cafeRequest, HttpStatus.CREATED);
    }

    @GetMapping("/cafe")
    public ResponseEntity<Iterable<Cafe>> getAllCity(){
        return new ResponseEntity<>(cafeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/cafe/{cafeId}")
    public ResponseEntity<Cafe> findCityById(
            @PathVariable Integer cafeId
    )
    {
        return ResponseEntity.ok(cafeRepository.findById(cafeId).orElse(null));
    }

    @GetMapping("/cafe/address")
    public ResponseEntity<Cafe> findCafeAddress(
            @RequestParam(name = "address") String address
    ){
        return new ResponseEntity<>(
                cafeRepository.findByAddress(address), HttpStatus.OK
        );
    }

    @PutMapping("/cafe/{id}")
    public ResponseEntity<Cafe> updateCafeById(
            @PathVariable Integer id,
            @RequestBody Cafe cafeRequest
    )
    {
        Cafe cafe = cafeRepository.findById(id).orElse(null);
        if (cafe != null && cafeRequest != null)
        {
            cafe.setCafeName(cafeRequest.getCafeName());
            cafe = cafeRepository.save(cafe);
        }
        return ResponseEntity.ok(cafe);
    }
    @DeleteMapping("/cafe/{cafeId}")
    public ResponseEntity<Cafe> deleteCafeById(
            @PathVariable Integer cafeId
    )
    {
        cafeRepository.deleteById(cafeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
