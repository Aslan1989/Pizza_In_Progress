package com.example.Pizza_In_Progress.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Time;
import java.util.*;

@Entity
@Table(name = "cafe")
@Data
@NoArgsConstructor
@ToString

public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "CAFE_NAME", length = 50, nullable = false, unique = false)
    private String cafeName;
    @Column(name = "CITY_NAME", length = 50, nullable = false, unique = false)
    private String city;
    @Column(name = "ADDRESS_NAME", length = 50, nullable = false, unique = false)
    private String address;
    @Column(name = "EMAIL_NAME", length = 50, nullable = false, unique = false)
    private String email;
    @Column(name = "PHONE", length = 50, nullable = false, unique = false)
    private String phone;

    public Cafe(String cafeName, String city, String address, String email, String phone) {
        this.cafeName = cafeName;
        this.city = city;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    @OneToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cafe_id")
    @JsonIgnore
      private Set<Pizza> pizza_menu = new HashSet<>();




}
