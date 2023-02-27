package com.example.Pizza_In_Progress.repository;

import com.example.Pizza_In_Progress.entity.Cafe;
import com.example.Pizza_In_Progress.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    public List<Pizza> findByCafe(Cafe cafe);
}
