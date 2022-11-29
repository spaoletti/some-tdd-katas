package org.tdd.katas.citysearch;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CitySearch {

    private final ArrayList<String> cities;

    public CitySearch(ArrayList<String> cities) {
        this.cities = cities;
    }

    public String search(String text) {
        if (text.length() < 2 && !"*".equals(text)) {
            return "";
        }
        return cities.stream()
                .filter(c -> "*".equals(text) || c.toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.joining(" "))
                .trim();
    }
}
