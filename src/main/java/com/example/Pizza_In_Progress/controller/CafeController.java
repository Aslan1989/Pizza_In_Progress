package com.example.Pizza_In_Progress.controller;

import com.example.Pizza_In_Progress.entity.Cafe;
import com.example.Pizza_In_Progress.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CafeController {

    private  CafeRepository cafeRepository;
    @Autowired
    public CafeController(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
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
    @DeleteMapping("/cafe/{cafeId}")
    public ResponseEntity<Cafe> deleteCafeById(
            @PathVariable Integer cafeId
    )
    {
        cafeRepository.deleteById(cafeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
