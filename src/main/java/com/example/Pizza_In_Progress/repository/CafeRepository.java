package com.example.Pizza_In_Progress.repository;

import com.example.Pizza_In_Progress.entity.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CafeRepository extends CrudRepository<Cafe, Integer> {

    Cafe findByAddress(String string);
}
