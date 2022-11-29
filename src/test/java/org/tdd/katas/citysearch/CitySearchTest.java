package org.tdd.katas.citysearch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CitySearchTest {

    private CitySearch citySearch;

    @BeforeEach
    public void beforeEach() {
        citySearch = new CitySearch(new ArrayList<>(Arrays.asList(
                "Paris", "Budapest", "Skopje", "Rotterdam", "Valencia", "Vancouver",
                "Amsterdam", "Vienna", "Sydney", "New York City", "London", "Bangkok",
                "Hong Kong", "Dubai", "Rome", "Istanbul"
        )));
    }

    @Test
    public void should_return_no_results_if_search_text_is_less_than_two_chars() {
        String result = citySearch.search("a");
        Assertions.assertEquals("", result);
    }

    @Test
    public void should_return_all_the_cities_starting_with_search_text() {
        String result = citySearch.search("Va");
        Assertions.assertEquals("Valencia Vancouver", result);
    }

    @Test
    public void search_text_should_be_case_insensitive() {
        String result = citySearch.search("va");
        Assertions.assertEquals("Valencia Vancouver", result);
    }

    @Test
    public void should_return_all_the_cities_containing_the_search_text() {
        String result = citySearch.search("ape");
        Assertions.assertEquals("Budapest", result);
    }

    @Test
    public void should_all_the_cities_if_the_search_text_is_asterisk() {
        String result = citySearch.search("*");



        String expectedResult =
                "Paris Budapest Skopje Rotterdam Valencia Vancouver Amsterdam Vienna " +
                "Sydney New York City London Bangkok Hong Kong Dubai Rome Istanbul";
        Assertions.assertEquals(expectedResult, result);
    }

}
