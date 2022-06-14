package com.example.country.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Country {
    private Integer id;
    private String description; //abstract ili comment
    private String capital_city;
    private String currency;
    private String thumbnail;
    private String name;
    private List<String> cities;

    public Country(Integer id, String description, String capital_city, String currency, String thumbnail, String name) {
        this.id = id;
        this.description = description;
        this.capital_city = capital_city;
        this.currency = currency;
        this.thumbnail = thumbnail;
        this.name = name;
        this.cities=new LinkedList<>();
    }
}
