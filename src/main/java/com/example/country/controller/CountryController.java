package com.example.country.controller;


import com.example.country.model.City;
import com.example.country.model.Country;
import com.example.country.queries.Queires;
import com.example.country.services.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@CrossOrigin("*")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{continent}")
    public List<Country> getAll(@PathVariable String continent){
        return countryService.getDataList(Queires.getCountries(continent));
    }

    @GetMapping("/country/{country}")
    public Country getCountry(@PathVariable String country){
        return countryService.getDataDetails(Queires.getCountryDetails(country));
    }

    @GetMapping("/city/{city}")
    public City getCity(@PathVariable String city){
        return countryService.getDataCityDetails(Queires.getCityDetails(city));
    }
}
