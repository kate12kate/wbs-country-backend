package com.example.country.services;

import com.example.country.model.City;
import com.example.country.model.Country;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService extends Dbpedia {

    public List<Country> getDataList(String url) {
        JSONArray countryJson = this.getData(url);
        return this.getCountriesFromJson(countryJson);
    }

    public Country getDataDetails(String url) {
        JSONArray countryJson = this.getData(url);
        return this.getCountryDetailsFromJson(countryJson);
    }

    public City getDataCityDetails(String url) {
        JSONArray countryJson = this.getData(url);
        return this.getCityDetails(countryJson);
    }

    private List<Country> getCountriesFromJson(JSONArray jsonArray) {
        List<Country> countries = jsonArray.stream().map((country) -> {
            Integer id = Integer.parseInt(this.getFromJson((JSONObject) country, "id"));
            String name = this.getFromJson((JSONObject) country, "name");
            String description = this.getFromJson((JSONObject) country, "desc");
            String capital_city = this.getFromJson((JSONObject) country, "capital_city").split("/")[4];
            String currency = this.getFromJson((JSONObject) country, "currency").split("/")[4];
            String thumbnail = this.getFromJson((JSONObject) country, "thumbnail");
            Country countryObj = new Country(id, description, capital_city, currency, thumbnail, name);
            return countryObj;
        }).collect(Collectors.toList());
        return countries;
    }

    //getCountryDetailsFromJson
    private Country getCountryDetailsFromJson(JSONArray jsonArray) {
        JSONObject country = (JSONObject) jsonArray.get(0);
        Integer id = Integer.parseInt(this.getFromJson(country, "id"));
        String name = this.getFromJson(country, "name");
        String description = this.getFromJson(country, "desc");
        String capital_city = this.getFromJson(country, "capital_city").split("/")[4];
        String currency = this.getFromJson(country, "currency").split("/")[4];
        String thumbnail = this.getFromJson(country, "thumbnail");
        Country countryObj = new Country(id, description, capital_city, currency, thumbnail, name);

        List<String> cities = jsonArray.stream().map((jb) -> {
            String[] cityArray = this.getFromJson((JSONObject) jb, "city").split("/");
            String city = cityArray[cityArray.length - 1];
            return city;
        }).distinct().collect(Collectors.toList());

        countryObj.setCities(cities);

        return countryObj;
    }

    //getCityDetails
    private City getCityDetails(JSONArray jsonArray) {
        JSONObject city = (JSONObject) jsonArray.get(0);
        String name = this.getFromJson(city, "name");
        String description = this.getFromJson(city, "desc");
        String thumbnail = this.getFromJson(city, "thumbnail");
        City cityObj = new City(name, description, thumbnail);
        return cityObj;
    }
}
